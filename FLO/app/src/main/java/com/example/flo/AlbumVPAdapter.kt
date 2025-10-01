package com.example.flo

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class AlbumVPAdapter(fragment: Fragment, private val album: Album): FragmentStateAdapter(fragment) {
    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> SongFragment()
            1 -> {
                val detailFragment = DetailFragment()
                val bundle = Bundle().apply {
                    putSerializable("Lilac", album)
                }
                detailFragment.arguments = bundle
                detailFragment
            }
            else -> VideoFragment()
        }
    }

    override fun getItemCount(): Int = 3
}