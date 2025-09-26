package com.example.umc_9th.flo_clone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.umc_9th.flo_clone.databinding.FragmentLockerMusicfileBinding
import kotlin.math.round

class MusicFileFragment: Fragment() {

    lateinit var binding: FragmentLockerMusicfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerMusicfileBinding.inflate(inflater, container, false)

        return binding.root
    }
}