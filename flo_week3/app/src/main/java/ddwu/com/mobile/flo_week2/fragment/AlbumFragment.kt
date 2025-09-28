package ddwu.com.mobile.flo_week2.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import ddwu.com.mobile.flo_week2.R
import ddwu.com.mobile.flo_week2.adapter.AlbumVPAdapter
import ddwu.com.mobile.flo_week2.data.Album
import ddwu.com.mobile.flo_week2.databinding.FragmentAlbumBinding
import ddwu.com.mobile.flo_week2.fragment.album.DetailFragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class AlbumFragment : Fragment() {
    private val information = arrayListOf("수록곡", "상세정보", "영상")
    private var param1: String? = null
    private var param2: String? = null

    lateinit var albumBinding: FragmentAlbumBinding
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
        albumBinding = FragmentAlbumBinding.inflate(inflater, container, false)

        val currentAlbum = arguments?.getSerializable("album") as? Album

        albumBinding.albumTitleTv.text = currentAlbum?.title
        albumBinding.albumSingerTv.text = currentAlbum?.singer
        albumBinding.coverImgIv.setImageResource(currentAlbum?.coverImg!!)
        albumBinding.btnAlbumQuit.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        val albumAdapter = AlbumVPAdapter(this, currentAlbum)
        albumBinding.albumVp.adapter = albumAdapter
        TabLayoutMediator(albumBinding.albumContentTb, albumBinding.albumVp) { tab, position ->
            tab.text = information[position]
        }.attach()

        // AlbumFragment.kt
        albumBinding.albumContentTb.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    Log.d("AlbumFragment", "탭 선택됨 - 위치: ${it.position}, 제목: ${it.text}")
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Log.d("AlbumFragment", "탭 선택 해제됨: ${tab?.text}")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Log.d("AlbumFragment", "탭 다시 선택됨: ${tab?.text}")
            }
        })


        return albumBinding.root
    }

    companion object {
            /**
             * Use this factory method to create a new instance of
             * this fragment using the provided parameters.
             *
             * @param param1 Parameter 1.
             * @param param2 Parameter 2.
             * @return A new instance of fragment AlbumFragment.
             */
            // TODO: Rename and change types and number of parameters
            @JvmStatic
            fun newInstance(param1: String, param2: String) =
                AlbumFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
        }
}
