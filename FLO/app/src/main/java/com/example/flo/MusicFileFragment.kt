package com.example.flo

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flo.databinding.FragmentLockerMusicfileBinding

class MusicFileFragment : Fragment() {

    private var _binding: FragmentLockerMusicfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLockerMusicfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dummyLocal = listOf(
            Song(title = "로컬 트랙 1", singer = "Unknown", coverImg = R.drawable.img_album_exp),
            Song(title = "로컬 트랙 2", singer = "Unknown", coverImg = R.drawable.img_album_exp2),
            Song(title = "로컬 트랙 3", singer = "Unknown", coverImg = R.drawable.img_album_exp3),
        )

        binding.musicfileRv.layoutManager = LinearLayoutManager(requireContext())
        binding.musicfileRv.adapter = SongRVAdapter(requireContext(), dummyLocal)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}