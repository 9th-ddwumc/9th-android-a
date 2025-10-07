package com.example.androidA

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidA.databinding.FragmentHomeBinding
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.homeMainAlbumIv.setOnClickListener {
            val album_info = Bundle().apply {
                    putString("album_title", binding.homeMainAlbumTitleTv.text.toString())
                    putString("album_singer", binding.homeMainAlbumSingerTv.text.toString())
                    putInt("album_cover", R.drawable.img_main_album)
            }

            findNavController().navigate(
                R.id.action_homeFragment_to_albumFragment,
                album_info
            )
            /*
            parentFragmentManager.beginTransaction()
            .replace(R.id.main_fragmentContainer, album_info)
            .addToBackStack(null)
            .commit()
            */
        }

        val bannerAdapter = BannerVPAdapter(this)
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_first_album_default))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp2))
        binding.homePannelBackgroundIv.adapter = bannerAdapter
        binding.homePannelBackgroundIv.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        return binding.root  // 최상위 뷰를 반환
    }

}