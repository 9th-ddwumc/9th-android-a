package ddwu.com.mobile.flo_week2.fragment.album

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ddwu.com.mobile.flo_week2.R
import ddwu.com.mobile.flo_week2.adapter.TrackAdapter
import ddwu.com.mobile.flo_week2.data.TrackDao
import ddwu.com.mobile.flo_week2.databinding.FragmentSongBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SongFragment : Fragment() {
    lateinit var binding: FragmentSongBinding
    private var param1: String? = null
    private var param2: String? = null

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
        binding = FragmentSongBinding.inflate(inflater, container, false)
        binding.songMixoffTg.setOnClickListener {
            setMixStatus(true)
        }

        binding.songMixonTg.setOnClickListener {
            setMixStatus(false)
        }

        // Track RecyclerView 설정
        val tracks = TrackDao().tracks
        val adapter = TrackAdapter(tracks)

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding.trackRecyclerView.layoutManager = layoutManager
        binding.trackRecyclerView.adapter = adapter

        return binding.root
    }

    fun setMixStatus(isMix: Boolean) {
        if(isMix) {
            binding.songMixonTg.visibility = View.VISIBLE
            binding.songMixoffTg.visibility = View.GONE
        }
        else {
            binding.songMixonTg.visibility = View.GONE
            binding.songMixoffTg.visibility = View.VISIBLE
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SongFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SongFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}