package com.example.umc_9th.flo_clone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.umc_9th.flo_clone.databinding.FragmentAlbumBinding
import com.google.android.material.tabs.TabLayoutMediator

class AlbumFragment: Fragment() {

    lateinit var binding: FragmentAlbumBinding

    private val tabInfo = arrayListOf("수록곡", "상세정보", "영상")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumBinding.inflate(inflater, container, false)

        val album = arguments?.getSerializable("album") as? Album

        binding.albumImgIv.setImageResource(album!!.albumCover)
        binding.albumTitleTv.setText(album.title)
        binding.albumSingerTv.setText(album.singer)
        binding.albumInfoTv.setText(album.releaseInfo)

        binding.albumBackIb.setOnClickListener {
            parentFragmentManager.popBackStack()
        }


        val albumAdapter = AlbumVPAdapter(this, album)
        binding.albumContentVp.adapter = albumAdapter
        TabLayoutMediator(binding.albumContentTb, binding.albumContentVp) {
            tab, position ->
            tab.text = tabInfo[position]
        }.attach()

        return binding.root
    }
}