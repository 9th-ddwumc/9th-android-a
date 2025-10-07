package com.example.androidA

import android.content.Intent
import android.os.Bundle
import com.example.androidA.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import android.app.Activity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    companion object {
        const val ALBUM_TITLE = "album_title"  // album_title은 Key 값
    }

    private val getResultText = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            val album_title = result.data?.getStringExtra(ALBUM_TITLE)  // 여기서 Key 값에 value 값을 넣음
            Toast.makeText(this, album_title, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.main_fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController

        // BottomNavigationView를 NavController와 연결
        binding.mainBnv.setupWithNavController(navController)

        binding.mainPlayerCl.setOnClickListener {
            val intent = Intent(this, SongActivity::class.java).apply {
                putExtra("playingTitle", binding.text1.text)
                putExtra("playingSinger", binding.text2.text)
            }
            getResultText.launch(intent)
        }

    }

    fun updateMiniPlayer(title: String, singer: String) {
        binding.text1.text = title
        binding.text2.text = singer
    }
}