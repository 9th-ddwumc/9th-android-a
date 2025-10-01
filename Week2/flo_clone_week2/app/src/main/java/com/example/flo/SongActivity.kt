package com.example.flo

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.flo.databinding.ActivitySongBinding

class SongActivity : AppCompatActivity() {
    lateinit var binding: ActivitySongBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val songTitle = intent.getStringExtra("playingTitle")
        val singer = intent.getStringExtra("playingSinger")

//        binding.songTitleTv.setText(songTitle)
//        binding.songSingerTv.setText(singer)

        binding.songDownIb.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply{
                putExtra(MainActivity.ALBUM_TITLE, binding.songTitleTv.text)
            }
            setResult(RESULT_OK, intent)
            finish()
        }
    }

}