package com.example.flo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.flo.databinding.ActivitySongBinding

class SongActivity: AppCompatActivity() {

    lateinit var binding: ActivitySongBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lilac = intent.getSerializableExtra("Lilac") as? Album ?: return

        binding.songTitleTv.setText(lilac.title)
        binding.songSingerTv.setText(lilac.singer)
        lilac.coverImg?.let { binding.songAlbumIv.setImageResource(it) }


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



//        // 이미지 버튼 색 바꾸기
//        binding.songRandomIb.setOnClickListener {
//            val selectColor = binding.root.resources.getColor(R.color.select_color, null)
//            val currentTint = binding.songRandomIb.imageTintList?.defaultColor
//
//            if (currentTint == selectColor) {
//                binding.songRandomIb.imageTintList = null
//            } else {
//                binding.songRandomIb.imageTintList = ColorStateList.valueOf(selectColor)
//            }
//        }


    }
}