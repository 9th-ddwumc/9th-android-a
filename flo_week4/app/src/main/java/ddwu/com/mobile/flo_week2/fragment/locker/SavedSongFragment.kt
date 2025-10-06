package ddwu.com.mobile.flo_week2.fragment.locker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ddwu.com.mobile.flo_week2.R
import ddwu.com.mobile.flo_week2.adapter.SavedSongAdapter
import ddwu.com.mobile.flo_week2.data.SavedSongDao
import ddwu.com.mobile.flo_week2.data.SavedSongDto
import ddwu.com.mobile.flo_week2.databinding.FragmentSavedSongBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SavedSongFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding : FragmentSavedSongBinding

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
        binding = FragmentSavedSongBinding.inflate(inflater, container, false)

        // SavedSong RecyclerView 설정
        val savedSongs = SavedSongDao().savedSongs
        val adapter = SavedSongAdapter(savedSongs)

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding.savedSongRecyclerView.layoutManager = layoutManager
        binding.savedSongRecyclerView.adapter = adapter

        adapter.setOnPlayButtonClickListener(object : SavedSongAdapter.onPlayButtonClickListener {
            override fun onPlayButtonClick(position: Int) {
                savedSongs.forEachIndexed { index, song ->
                    song.isPlaying = (index == position && !song.isPlaying)
                }
                adapter.notifyDataSetChanged()
            }
        })
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SavedSongFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SavedSongFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}