package com.example.umc_9th.flo_clone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.umc_9th.flo_clone.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val lilac = arguments?.getSerializable("Lilac") as? Album

        binding.homeTodayMusicAlbum1Iv.setImageResource(lilac!!.albumCover)
        binding.homeTodayMusicTitle1Tv.setText(lilac.title)
        binding.homeTodayMusicSinger1Tv.setText(lilac.singer)

        binding.homeTodayMusicAlbum1Layout.setOnClickListener {
            val albumFragment = AlbumFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("Lilac", lilac)
                }
            }
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, albumFragment)
                .addToBackStack(null)
                .commit()
        }

        val bannerAdapter = BannerVPAdapter(this)
        val indicator = binding.homeBannerIndicator

        bannerAdapter.addFragment(BannerFragment(R.drawable.img_first_album_default))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_first_album_default))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_first_album_default))
        binding.homeBannerVp.adapter = bannerAdapter
        indicator.setViewPager(binding.homeBannerVp)

        binding.homeBannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        return binding.root
    }
}