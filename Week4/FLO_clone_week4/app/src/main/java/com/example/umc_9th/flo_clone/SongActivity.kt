package com.example.umc_9th.flo_clone

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.umc_9th.flo_clone.databinding.ActivitySongBinding
import kotlinx.coroutines.selects.select
import java.util.ArrayList

class SongActivity: AppCompatActivity() {

    lateinit var binding: ActivitySongBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val albumData = intent.getSerializableExtra("albumData") as? ArrayList<Album> ?: return


        // song activity 종료
        binding.songDownIb.setOnClickListener {
            finish()
        }


        // play <-> pause 전환
        binding.songPlayIb.setOnClickListener {
            binding.songPlayIb.visibility = View.GONE
            binding.songPauseIb.visibility = View.VISIBLE
        }

        binding.songPauseIb.setOnClickListener {
            binding.songPauseIb.visibility = View.GONE
            binding.songPlayIb.visibility = View.VISIBLE
        }


        // repeat interact
        binding.songRepeatIb.setOnClickListener {
            binding.songRepeatIb.visibility = View.GONE
            binding.songRepeatActIb.visibility = View.VISIBLE
        }
        binding.songRepeatActIb.setOnClickListener {
            binding.songRepeatActIb.visibility = View.GONE
            binding.songRepeatIb.visibility = View.VISIBLE
        }


        // random interact
        binding.songRandomIb.setOnClickListener {
            binding.songRandomIb.visibility = View.GONE
            binding.songRandomActIb.visibility = View.VISIBLE
        }
        binding.songRandomActIb.setOnClickListener {
            binding.songRandomActIb.visibility = View.GONE
            binding.songRandomIb.visibility = View.VISIBLE
        }
    }
}