package com.example.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flo.databinding.FragmentLockerSavedalbumBinding

class SavedAlbumFragment : Fragment() {

    private var _binding: FragmentLockerSavedalbumBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: AlbumLockerRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLockerSavedalbumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dummy = arrayListOf(
            Album(title = "LILAC", singer = "IU", coverImg = R.drawable.img_album_exp2),
            Album(title = "New Jeans", singer = "NewJeans", coverImg = R.drawable.img_album_exp3),
            Album(title = "BUTTER", singer = "BTS", coverImg = R.drawable.img_album_exp),
            Album(title = "SEVEN", singer = "Jung Kook", coverImg = R.drawable.img_album_exp4),
        )

        adapter = AlbumLockerRVAdapter().apply {
            setMyItemClickListener(object : AlbumLockerRVAdapter.MyItemClickListener {
                override fun onRemoveAlbum(position: Int) {
                    removeAlbum(position)
                }

                override fun onClick(position: Int) {
                }
            })
        }

        binding.savedalbumRv.layoutManager = LinearLayoutManager(requireContext())
        binding.savedalbumRv.setHasFixedSize(true)
        binding.savedalbumRv.adapter = adapter

        adapter.addAlbums(dummy)


        // pause <-> play (전체듣기)

        // val playButton = binding.savedalbumPlayAllIb
        // var isPlaying = false

        // playButton.setOnClickListener {
        //     if (isPlaying) {
        //         playButton.setImageResource(R.drawable.btn_miniplay_mvplay)
        //         isPlaying = false
        //     } else {
        //         playButton.setImageResource(R.drawable.btn_miniplay_mvpause)
        //         isPlaying = true
        //     }
        // }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}