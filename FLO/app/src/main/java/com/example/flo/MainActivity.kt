package com.example.flo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.flo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    val lilac = Album("LILAC", "아이유 (IU)", R.drawable.img_album_exp2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainPlayingTitleTv.setText(lilac.title)
        binding.mainPlayingSingerTv.setText(lilac.singer)

        binding.mainPlayerCl.setOnClickListener {
            val intent = Intent(this, SongActivity::class.java).apply {
                putExtra("Lilac", lilac)
            }
            startActivity(intent)
        }

        initBottomNavigation()
    }

    fun updateMiniPlayer(title: String, singer: String) {
        binding.mainPlayingTitleTv.text = title
        binding.mainPlayingSingerTv.text = singer
        binding.mainPlayerCl.visibility = View.VISIBLE
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