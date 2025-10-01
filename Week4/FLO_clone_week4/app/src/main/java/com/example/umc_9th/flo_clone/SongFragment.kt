package com.example.umc_9th.flo_clone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_9th.flo_clone.databinding.FragmentSongBinding

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

        val album = arguments?.getSerializable("album") as Album

        val songAdapter = AlbumSongRVAdapter(album.songList)
        binding.songRV.adapter = songAdapter
        binding.songRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        return binding.root
    }
}