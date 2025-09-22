package com.example.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flo.databinding.FragmentHomeBinding
import java.io.Serializable

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

    data class Album(
        val title: String,
        val singer: String,
        val coverResId: Int
    ) : Serializable

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val lilacAlbum = Album(
            title = "LILAC",
            singer = "아이유 (IU)",
            coverResId = R.drawable.img_album_exp2
        )

        binding.homeTodayMusicAlbumTitleTv.setText(lilacAlbum.title)
        binding.homeTodayMusicAlbumSingerTv.setText(lilacAlbum.singer)
        binding.homeTodayMusicAlbumIv.setImageResource(lilacAlbum.coverResId)

        binding.homeTodayMusicAlbumIv.setOnClickListener {

            val albumFragment = AlbumFragment().apply {
                arguments = Bundle().apply {
                    putString("album_title", lilacAlbum.title)
                    putString("album_singer", lilacAlbum.singer)
                    putInt("album_cover", lilacAlbum.coverResId)
                }
            }
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, albumFragment)
                .addToBackStack(null)
                .commit()
        }
        return binding.root
    }
}