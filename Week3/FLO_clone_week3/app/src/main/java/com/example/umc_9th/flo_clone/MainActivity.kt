package com.example.umc_9th.flo_clone

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.umc_9th.flo_clone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    val lilac = Album("LILAC", "임수호, Dr.JO, 웅킴, N!ko", "라일락", "아이유 (IU)", R.drawable.img_album_exp2,
        "2021.03.25 | 정규 | 댄스 팝",
        "나리는 꽃가루에", "눈이 따끔해 아야", "03:39")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainPlayingTitleTv.setText(lilac.songTitle)
        binding.mainPlayingSingerTv.setText(lilac.singer)

        binding.mainPlayerCl.setOnClickListener {
            val intent = Intent(this, SongActivity::class.java).apply {
                putExtra("Lilac", lilac)
            }
            startActivity(intent)
        }

        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        val homeFragment = HomeFragment().apply {
            arguments = Bundle().apply {
                putSerializable("Lilac", lilac)
            }
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, homeFragment)
            .commitAllowingStateLoss()

        binding.mainBnv.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.homeFragment -> {
                    val homeFragment = HomeFragment().apply {
                        arguments = Bundle().apply {
                            putSerializable("Lilac", lilac)
                        }
                    }
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, homeFragment)
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.lookFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, LookFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.searchFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, SearchFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.lockerFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, LockerFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}