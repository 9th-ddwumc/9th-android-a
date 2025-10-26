package com.example.umc_9th.flo_clone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_9th.flo_clone.databinding.FragmentLockerSavedsongBinding
import java.io.Serializable
import java.util.ArrayList

class SavedSongFragment: Fragment() {

    lateinit var binding: FragmentLockerSavedsongBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerSavedsongBinding.inflate(inflater, container, false)

        val albumData = arguments?.getSerializable("albumData") as ArrayList<Album>
        // 더미 데이터 처리
        val allSongs = ArrayList(albumData.flatMap { it.songList })

        val lockerSongAdapter = LockerSongRVAdapter(allSongs)
        binding.savedsongRv.adapter = lockerSongAdapter
        binding.savedsongRv.layoutManager  = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


        return binding.root
    }
}