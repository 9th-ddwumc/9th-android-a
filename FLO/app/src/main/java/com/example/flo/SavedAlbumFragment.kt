package com.example.flo

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flo.databinding.FragmentLockerSavedalbumBinding

class SavedAlbumFragment : Fragment() {

    private var _binding: FragmentLockerSavedalbumBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLockerSavedalbumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dummy = arrayListOf(
            Album(title = "LILAC", singer = "IU", coverImg = R.drawable.img_album_exp2),
            Album(title = "New Jeans", singer = "NewJeans", coverImg = R.drawable.img_album_exp),
            Album(title = "BUTTER", singer = "BTS", coverImg = R.drawable.img_album_exp),
            Album(title = "SEVEN", singer = "Jung Kook", coverImg = R.drawable.img_album_exp),
        )

        val adapter = AlbumLockerRVAdapter()
        binding.savedalbumRv.layoutManager = LinearLayoutManager(requireContext())
        binding.savedalbumRv.adapter = adapter
        adapter.addAlbums(dummy)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}