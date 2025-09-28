package ddwu.com.mobile.flo_week2.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ddwu.com.mobile.flo_week2.fragment.LockerFragment
import ddwu.com.mobile.flo_week2.fragment.locker.SavedAlbumFragment
import ddwu.com.mobile.flo_week2.fragment.locker.SavedSongFragment
import ddwu.com.mobile.flo_week2.fragment.locker.SongfileFragment

class LockerVPAdapter(fa: LockerFragment) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> SavedSongFragment()
            1 -> SongfileFragment()
            else -> SavedAlbumFragment()
        }
    }
}