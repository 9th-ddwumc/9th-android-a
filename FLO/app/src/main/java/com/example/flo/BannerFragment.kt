package com.example.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flo.databinding.FragmentBannnerBinding

class BannerFragment(val imgRes: Int): Fragment() {

    lateinit var binding: FragmentBannnerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBannnerBinding.inflate(inflater, container, false)

        binding.bannerImageIv.setImageResource(imgRes)

        return binding.root
    }
}