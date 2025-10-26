package com.example.androidA

import android.content.Intent
import android.os.Bundle
import com.example.androidA.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import android.app.Activity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
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
        Thread.sleep(3000) // 3초 지연
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Chapter00)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.main_fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController

        // BottomNavigationView를 NavController와 연결
        binding.mainBnv.setupWithNavController(navController)

        val song = Song(binding.text1.text.toString(), binding.text2.text.toString(),null, null, 0,60,false)

        binding.mainPlayerCl.setOnClickListener {
            val intent = Intent(this, SongActivity::class.java).apply {
                putExtra("title", song.title)
                putExtra("singer", song.singer)
                putExtra("rank", song.rank)
                putExtra("coverImg", song.coverImg)
                putExtra("second", song.second)
                putExtra("playTime", song.playTime)
                putExtra("isPlaying", song.isPlaying)
            }
            getResultText.launch(intent)
        }

    }

    fun updateMiniPlayer(title: String, singer: String, isPlaying: Boolean) {
        binding.text1.text = title
        binding.text2.text = singer

        if (isPlaying) {
            binding.mainMiniplayerBtn.setImageResource(R.drawable.btn_miniplay_pause)
        } else {
            binding.mainMiniplayerBtn.setImageResource(R.drawable.btn_player_play)
        }
    }
}