package com.example.umc_9th.flo_clone

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.res.ColorStateList
import android.os.Bundle
import android.os.IBinder
import android.os.PersistableBundle
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.umc_9th.flo_clone.databinding.ActivitySongBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select
import java.util.ArrayList

class SongActivity: AppCompatActivity() {

    lateinit var binding: ActivitySongBinding

    private var musicService: MusicService? = null
    private var isBound = false
    private var updateJob: Job? = null

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MusicService.MusicBinder
            musicService = binder.getService()
            isBound = true
            updateUI()
            updateSeekbar()
        }

        private fun updateUI() {
            musicService?.let {
                binding.songSeekbarSb.max = it.getDuration()
                binding.songEndTimeTv.text = milliTotime(it.getDuration())
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val albumData = intent.getSerializableExtra("albumData") as? ArrayList<Album> ?: return

        val musicServiceIntent = Intent(this, MusicService::class.java).apply {
            putExtra("songTitle", albumData[0].title ?: "Unknown")
            putExtra("songArtist", albumData[0].singer ?: "Unknown")
            putExtra("isPlaying", true)
        }
        ContextCompat.startForegroundService(this, musicServiceIntent)
        bindService(musicServiceIntent, connection, Context.BIND_AUTO_CREATE)


        //재생&멈춤 버튼 터치 시 MediaPlayer에 반영
        binding.songPlayIb.setOnClickListener {
            musicService?.playMusic()
            binding.songPlayIb.visibility = View.GONE
            binding.songPauseIb.visibility = View.VISIBLE
        }
        binding.songPauseIb.setOnClickListener {
            musicService?.pauseMusic()
            binding.songPauseIb.visibility = View.GONE
            binding.songPlayIb.visibility = View.VISIBLE
        }

        // 이전 곡, 다음 곡
        binding.songPreviousIb.setOnClickListener {
            musicService?.seekTo(0)
            musicService?.playMusic()
        }
        binding.songNextIb.setOnClickListener {
            musicService?.seekTo(0)
            musicService?.playMusic()
        }


        //SeekBar 터치 시 MediaPlayer에 영
        binding.songSeekbarSb.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    musicService?.seekTo(progress)
                    binding.songStartTimeTv.text = milliTotime(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })


        // 버튼 클릭 이벤트
        binding.songDownIb.setOnClickListener {
            finish()
        }

        binding.songRandomIb.setOnClickListener {
            binding.songRandomIb.visibility = View.GONE
            binding.songRandomActIb.visibility = View.VISIBLE
        }

        binding.songRandomActIb.setOnClickListener {
            binding.songRandomActIb.visibility = View.GONE
            binding.songRandomIb.visibility = View.VISIBLE

        }

        binding.songRepeatIb.setOnClickListener {
            binding.songRepeatIb.visibility = View.GONE
            binding.songRepeatActIb.visibility = View.VISIBLE
        }

        binding.songRepeatActIb.setOnClickListener {
            binding.songRepeatActIb.visibility = View.GONE
            binding.songRepeatIb.visibility = View.VISIBLE

        }
    }

    private fun milliTotime(ms: Int): String {
        val totalSeconds = ms / 1000          // 밀리초 → 초
        val minutes = totalSeconds / 60       // 분
        val seconds = totalSeconds % 60       // 초
        return String.format("%02d:%02d", minutes, seconds) // "분:초" 포맷
    }

    //코루틴을 이용해, 음악 진행도를 SeekBar에 반영
    private fun updateSeekbar() {
        updateJob?.cancel()
        updateJob = lifecycleScope.launch(Dispatchers.Main) {
            while (isBound) {
                delay(100)
                val currentPosition = musicService!!.getCurrentPosition()
                binding.songSeekbarSb.progress = currentPosition
                binding.songStartTimeTv.text = milliTotime(currentPosition)
            }
        }
    }
}