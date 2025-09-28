package com.example.androidA

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.androidA.databinding.ActivitySongBinding

class SongActivity : AppCompatActivity() {
    lateinit var binding: ActivitySongBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.text4.text = intent.getStringExtra("playingTitle")
        binding.text3.text = intent.getStringExtra("playingSinger")

        binding.image2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply{
                putExtra(MainActivity.ALBUM_TITLE, binding.text4.text)
            }
            setResult(RESULT_OK, intent)
            finish()
        }

        binding.songMiniplayerIv.setOnClickListener {
            setPlayerStatus(false)
        }
        binding.songPauseIv.setOnClickListener {
            setPlayerStatus(true)
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
    }

    fun setPlayerStatus(isPlaying : Boolean) {
        if(isPlaying) {
            binding.songMiniplayerIv.visibility = View.VISIBLE
            binding.songPauseIv.visibility = View.GONE
        }
        else {
            binding.songMiniplayerIv.visibility = View.GONE
            binding.songPauseIv.visibility = View.VISIBLE
        }
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
}