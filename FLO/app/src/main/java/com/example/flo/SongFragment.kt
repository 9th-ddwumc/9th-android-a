package com.example.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flo.databinding.FragmentSongBinding

class SongFragment: Fragment() {

    lateinit var binding: FragmentSongBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSongBinding.inflate(inflater, container, false)


        // mix of <-> off 전환
        binding.songMixoffTg.setOnClickListener {
            binding.songMixoffTg.visibility = View.GONE
            binding.songMixonTg.visibility = View.VISIBLE
        }
        binding.songMixonTg.setOnClickListener {
            binding.songMixonTg.visibility = View.GONE
            binding.songMixoffTg.visibility = View.VISIBLE
        }


        // 리사이클러뷰 더미데이터 설정
        binding.songRv.layoutManager = LinearLayoutManager(requireContext())

        val dummySongs = listOf(
            Song(title = "Lilac", singer = "아이유", coverImg = R.drawable.img_album_exp2),
            Song(title = "Coin", singer = "아이유", coverImg = R.drawable.img_album_exp2),
            Song(title = "Flu", singer = "아이유", coverImg = R.drawable.img_album_exp2),
            Song(title = "봄 안녕 봄", singer = "아이유", coverImg = R.drawable.img_album_exp2),
            Song(title = "Celebrity", singer = "아이유", coverImg = R.drawable.img_album_exp2)
        )

        binding.songRv.adapter = SongRVAdapter(requireContext(), dummySongs)


        return binding.root
    }
}