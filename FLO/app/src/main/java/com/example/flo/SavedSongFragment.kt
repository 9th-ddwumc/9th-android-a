package com.example.flo

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flo.databinding.FragmentLockerSavedsongBinding

class SavedSongFragment : Fragment() {

    private var _binding: FragmentLockerSavedsongBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLockerSavedsongBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dummy = mutableListOf(
            Song(title = "밤양갱", singer = "BIBI", coverImg = R.drawable.img_album_exp),
            Song(title = "Hype Boy", singer = "NewJeans", coverImg = R.drawable.img_album_exp2),
            Song(title = "Love wins all", singer = "IU", coverImg = R.drawable.img_album_exp3)
        )

        binding.savedsongRv.layoutManager = LinearLayoutManager(requireContext())
        binding.savedsongRv.adapter = SongRVAdapter(requireContext(), dummy)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}