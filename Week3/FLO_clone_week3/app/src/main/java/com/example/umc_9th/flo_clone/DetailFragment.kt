package com.example.umc_9th.flo_clone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.umc_9th.flo_clone.databinding.FragmentDetailBinding

class DetailFragment: Fragment() {

    lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        val lilac = arguments?.getSerializable("Lilac") as Album

        binding.detailAboutAlbumTv.setText("이 앨범의 이름은 ${lilac.title} 입니다.")
        binding.detailAboutComposerTv.setText("이 앨범의 작곡가는 ${lilac.composer} 입니다.")

        return binding.root
    }
}