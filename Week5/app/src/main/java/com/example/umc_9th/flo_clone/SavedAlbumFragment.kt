package com.example.umc_9th.flo_clone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_9th.flo_clone.databinding.FragmentLockerSavedalbumBinding
import java.util.ArrayList

class SavedAlbumFragment: Fragment() {

    lateinit var binding: FragmentLockerSavedalbumBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerSavedalbumBinding.inflate(inflater, container, false)

        val albumData = arguments?.getSerializable("albumData") as ArrayList<Album>

        val lockerAlbumAdapter = LockerAlbumRVAdapter(albumData)
        binding.savedalbumRv.adapter = lockerAlbumAdapter
        binding.savedalbumRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        return binding.root
    }
}