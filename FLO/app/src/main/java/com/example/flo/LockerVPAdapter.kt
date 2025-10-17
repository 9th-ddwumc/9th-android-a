package com.example.flo

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class LockerVPAdapter(fragment: Fragment): FragmentStateAdapter(fragment){
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> SavedSongFragment()
            1 -> MusicFileFragment()
            else -> SavedAlbumFragment()
        }
    }

    override fun getItemCount(): Int = 3


}