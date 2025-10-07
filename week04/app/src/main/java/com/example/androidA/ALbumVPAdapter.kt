package com.example.androidA

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ALbumVPAdapter(fragment: Fragment,
                     private val albumTitle: String,
                     private val artistName: String
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> SongFragment()
            1 -> DetailFragment().apply {
                arguments = Bundle().apply {
                    putString("ALBUM_TITLE", albumTitle)
                    putString("ARTIST_NAME", artistName)
                }
            }
            else -> VideoFragment()
        }
    }
}