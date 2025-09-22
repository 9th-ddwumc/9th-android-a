package com.bokchi.umc.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bokchi.umc.flo.databinding.FragmentHomeBinding

class LockerFragment: Fragment() {

    private lateinit var Lockerbinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Lockerbinding = FragmentHomeBinding.inflate(inflater, container, false)
        return Lockerbinding.root
    }
}