package com.example.umc_9th.flo_clone

import android.os.Bundle
import android.widget.Adapter
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class LockerVPAdapter(fragment: Fragment, private val albumData: ArrayList<Album>): FragmentStateAdapter(fragment){
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                val savedSongFragment = SavedSongFragment()
                val bundle = Bundle().apply {
                    putSerializable("albumData", albumData)
                }
                savedSongFragment.arguments = bundle
                savedSongFragment
            }
            1 -> MusicFileFragment()
            else -> SavedAlbumFragment()
        }
    }

    override fun getItemCount(): Int = 3


}