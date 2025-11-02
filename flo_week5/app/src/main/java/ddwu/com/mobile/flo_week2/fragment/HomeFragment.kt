package ddwu.com.mobile.flo_week2.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import ddwu.com.mobile.flo_week2.MainActivity
import ddwu.com.mobile.flo_week2.adapter.BannerVPAdapter
import ddwu.com.mobile.flo_week2.R
import ddwu.com.mobile.flo_week2.adapter.AlbumAdapter
import ddwu.com.mobile.flo_week2.data.AlbumDto
import ddwu.com.mobile.flo_week2.data.AlbumDao
import ddwu.com.mobile.flo_week2.databinding.FragmentHomeBinding
import ddwu.com.mobile.flo_week2.fragment.banner.BannerFragment
import java.util.Timer
import kotlin.concurrent.scheduleAtFixedRate

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private val timer = Timer()
    private val handler = Handler(Looper.getMainLooper())
    lateinit var homeBinding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)


        // ViewPager2 설정
        val imageList = listOf(
            R.drawable.img_first_album_default,
            R.drawable.discovery_banner_aos,
            R.drawable.img_potcast_exp
        )
        val bannerAdapter = BannerVPAdapter(this, imageList)
        homeBinding.homeViewPager.adapter = bannerAdapter
        homeBinding.homeViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        homeBinding.homePannelIndicator.setViewPager(homeBinding.homeViewPager)

        // 자동 슬라이드
        startAutoSlide(bannerAdapter)

        // Album RecyclerView 설정
        val albums = AlbumDao().albums
        val adapter = AlbumAdapter(albums)

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        homeBinding.homeRecyclerView.layoutManager = layoutManager
        homeBinding.homeRecyclerView.adapter = adapter

        adapter.setOnItemClickListener(object : AlbumAdapter.OnItemClickListener {
            override fun onItemClick(album: AlbumDto) {
                val bundle = Bundle().apply {
                    putSerializable("album", album)
                }

                val albumFragment = AlbumFragment().apply {
                    arguments = bundle
                }

                parentFragmentManager.beginTransaction()
                    .replace(R.id.main_fragmentContainer, albumFragment)
                    .addToBackStack(null)
                    .commit()
            }
        })

        adapter.setOnPlayButtonClickListener(object : AlbumAdapter.OnPlayButtonClickListener {
            override fun onPlayButtonClick(album: AlbumDto) {
                (requireActivity() as MainActivity).onPlayButtonClicked(album)
            }
        })

        return homeBinding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun startAutoSlide(adapter: BannerVPAdapter) {
        timer.scheduleAtFixedRate(3000, 3000){
            handler.post{
                val nextItem = homeBinding.homeViewPager.currentItem + 1
                if(nextItem < adapter.itemCount){
                    homeBinding.homeViewPager.currentItem = nextItem
                }
                else{
                    homeBinding.homeViewPager.currentItem = 0
                }
            }
        }
    }
}