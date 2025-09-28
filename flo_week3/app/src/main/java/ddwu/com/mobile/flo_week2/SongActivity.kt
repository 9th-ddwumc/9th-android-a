package ddwu.com.mobile.flo_week2

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ddwu.com.mobile.flo_week2.data.Album
import ddwu.com.mobile.flo_week2.databinding.ActivitySongBinding

class SongActivity : AppCompatActivity() {
    lateinit var binding: ActivitySongBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // miniPlayer -> SongActivity
        val song = intent.getSerializableExtra("album") as Album
        binding.songMusicTitleTv.text = song.title
        binding.songSingerNameTv.text = song.singer
        binding.songAlbumIv.setImageResource(song.coverImg?:R.drawable.img_album_exp)


        // quit
        binding.songDownIb.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("title", binding.songMusicTitleTv.text.toString())
            resultIntent.putExtra("singer", binding.songSingerNameTv.text.toString())
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        // play
        binding.songMiniplayerIv.setOnClickListener {
            setPlayerStatus(false)
        }

        binding.songPauseIv.setOnClickListener {
            setPlayerStatus(true)
        }

        //repeat
        binding.songRepeatIv.setOnClickListener {
            setRepeatStatus(true)
        }

        binding.songRepeatActiveIv.setOnClickListener {
            setRepeatStatus(false)
        }

        //random
        binding.songRandomIv.setOnClickListener {
            setRandomStatus(true)
        }

        binding.songRandomActiveIv.setOnClickListener {
            setRandomStatus(false)
        }

    }

    fun setPlayerStatus(isPlaying: Boolean) {
        if(isPlaying) {
            binding.songMiniplayerIv.visibility = View.VISIBLE
            binding.songPauseIv.visibility = View.GONE
        }
        else{
            binding.songMiniplayerIv.visibility = View.GONE
            binding.songPauseIv.visibility = View.VISIBLE
        }
    }

    fun setRepeatStatus(isRepeat: Boolean) {
        if(isRepeat){
            binding.songRepeatActiveIv.visibility = View.VISIBLE
            binding.songRepeatIv.visibility = View.GONE
        }
        else{
            binding.songRepeatActiveIv.visibility = View.GONE
            binding.songRepeatIv.visibility = View.VISIBLE
        }
    }

    fun setRandomStatus(isRandom: Boolean) {
        if(isRandom){
            binding.songRandomActiveIv.visibility = View.VISIBLE
            binding.songRandomIv.visibility = View.GONE
        }
        else{
            binding.songRandomActiveIv.visibility = View.GONE
            binding.songRandomIv.visibility = View.VISIBLE
        }
    }
}