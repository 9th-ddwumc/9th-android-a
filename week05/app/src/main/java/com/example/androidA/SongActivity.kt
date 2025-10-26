package com.example.androidA

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.androidA.databinding.ActivitySongBinding

class SongActivity : AppCompatActivity() {
    lateinit var binding: ActivitySongBinding
    lateinit var song: Song
    lateinit var timer: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSong()
        setPlayer(song)
        initClickListener()

        binding.image2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply{
                putExtra(MainActivity.ALBUM_TITLE, binding.text4.text)
            }
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun initClickListener() {
        binding.songMiniplayerIv.setOnClickListener {
            setPlayerStatus(true)
        }
        binding.songPauseIv.setOnClickListener {
            setPlayerStatus(false)
        }

        binding.mixedPlayIv.setOnClickListener {
            setMixedStatus(false)
        }
        binding.mixedPlayOnIv.setOnClickListener {
            setMixedStatus(true)
        }

        binding.repeatPlayIv.setOnClickListener {
            setRepeatStatus(false)
        }
        binding.repeatPlayOnIv.setOnClickListener {
            setRepeatStatus(true)
        }

        binding.songPreviousIv.setOnClickListener {
            timer.interrupt()
            timer = Timer(song.playTime, true)
            timer.start()
            binding.songStartTimeTv.text = "00:00"
            binding.songProgressSb.progress = 0
            setPlayerStatus(true)
        }

        binding.songNextIv.setOnClickListener {
            timer.interrupt()
            timer = Timer(song.playTime, true)
            timer.start()
            binding.songStartTimeTv.text = "00:00"
            binding.songProgressSb.progress = 0
            setPlayerStatus(true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.interrupt()
    }

    private fun initSong() {
        if(intent.hasExtra("title") && intent.hasExtra("singer")) {
            song = Song(
                intent.getStringExtra("title")!!,
                intent.getStringExtra("singer")!!,
                intent.getIntExtra("rank", 0),
                intent.getIntExtra("coverImg", 0),
                intent.getIntExtra("second", 0),
                intent.getIntExtra("playTime", 0),
                intent.getBooleanExtra("isPlaying", false)
            )
        }
        startTimer()
    }

    fun setPlayerStatus(isPlaying : Boolean) {
        song.isPlaying = isPlaying
        timer.isPlaying = isPlaying

        if(isPlaying){
            binding.songMiniplayerIv.visibility = View.GONE
            binding.songPauseIv.visibility = View.VISIBLE
        } else {
            binding.songMiniplayerIv.visibility = View.VISIBLE
            binding.songPauseIv.visibility = View.GONE
        }
    }

    private fun setPlayer(song: Song) {
        binding.text4.text = intent.getStringExtra("title")!!
        binding.text3.text = intent.getStringExtra("singer")!!
        binding.songStartTimeTv.text = String.format("%02d:%02d", song.second / 60, song.second % 60)
        binding.songEndTimeTv.text = String.format("%02d:%02d", song.playTime / 60, song.playTime % 60)
        binding.songProgressSb.progress = (song.second * 1000 / song.playTime)

        setPlayerStatus(song.isPlaying)
    }

    fun setMixedStatus(isPlaying : Boolean) {
        if(isPlaying) {
            binding.mixedPlayIv.visibility = View.VISIBLE
            binding.mixedPlayOnIv.visibility = View.GONE
        }
        else {
            binding.mixedPlayIv.visibility = View.GONE
            binding.mixedPlayOnIv.visibility = View.VISIBLE
        }
    }

    fun setRepeatStatus(isPlaying : Boolean) {
        if(isPlaying) {
            binding.repeatPlayIv.visibility = View.VISIBLE
            binding.repeatPlayOnIv.visibility = View.GONE
        }
        else {
            binding.repeatPlayIv.visibility = View.GONE
            binding.repeatPlayOnIv.visibility = View.VISIBLE
        }
    }

    private fun startTimer() {
        timer = Timer(song.playTime,song.isPlaying)
        timer.start()
    }

    inner class Timer(private val playTime: Int, var isPlaying: Boolean = true): Thread() {
        private var second : Int = 0
        private var mills : Float = 0f

        override fun run() {
            super.run()
            try {
                while (true) {
                    if (second >= playTime) {
                        break
                    }

                    if (isPlaying) {
                        sleep(50)
                        mills += 50

                        runOnUiThread {
                            binding.songProgressSb.progress = ((mills / playTime) * 100).toInt()
                        }
                        if (mills % 1000 == 0f) {
                            runOnUiThread {
                                binding.songStartTimeTv.text = String.format("%02d:%02d", second / 60, second % 60)
                            }
                            second++
                        }
                    }
                }

            } catch (e: InterruptedException) {
                Log.d("Song", "쓰레드가 죽었습니다. ${e.message}")
            }

        }
    }
}