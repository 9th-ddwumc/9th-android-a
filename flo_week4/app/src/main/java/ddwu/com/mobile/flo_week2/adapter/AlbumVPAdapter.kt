package ddwu.com.mobile.flo_week2.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ddwu.com.mobile.flo_week2.data.AlbumDto
import ddwu.com.mobile.flo_week2.fragment.album.DetailFragment
import ddwu.com.mobile.flo_week2.fragment.album.SongFragment
import ddwu.com.mobile.flo_week2.fragment.album.VideoFragment

class AlbumVPAdapter(
    fragment:Fragment,
    val currentAlbum: AlbumDto?) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> SongFragment()
            1 ->  DetailFragment().apply { // "상세정보" 탭
                arguments = Bundle().apply {
                    currentAlbum?.let {
                        putSerializable("currentAlbum", it)
                    }
                }
            }
            else -> VideoFragment()
        }
    }
}