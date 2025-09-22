package com.bokchi.umc.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bokchi.umc.flo.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {

    private lateinit var Homebinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Homebinding = FragmentHomeBinding.inflate(inflater, container, false)
        return Homebinding.root
    }


}