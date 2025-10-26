package com.example.umc_9th.flo_clone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.umc_9th.flo_clone.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {
    lateinit var binding: FragmentHomeBinding

    private lateinit var albumData: ArrayList<Album>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)


        val albumData = arguments?.getSerializable("albumData") as ArrayList<Album>

        val albumRVAdapter = AlbumRVAdapter(albumData)
        binding.homeTodayMusicRv.adapter = albumRVAdapter
        binding.homeTodayMusicRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        albumRVAdapter.setMyItemClickListener(object : AlbumRVAdapter.MyItemClickListener{
            override fun onItemClick(album: Album) {
                val albumFragment = AlbumFragment()
                val bundle = Bundle()
                bundle.putSerializable("album", album)
                albumFragment.arguments = bundle

                parentFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, albumFragment)
                    .addToBackStack(null)
                    .commit()
            }
        })

        albumRVAdapter.setPlayButtonClickListener(object : AlbumRVAdapter.PlayButtonClickListener{
            override fun onPlayBtnClick(album: Album) {
                val mainActivity = activity as MainActivity

                mainActivity.binding.mainPlayingTitleTv.text = album.songList[0].title
                mainActivity.binding.mainPlayingSingerTv.text = album.songList[0].singer
            }
        })


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