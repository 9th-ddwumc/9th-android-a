package com.bokchi.umc.flo

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bokchi.umc.flo.databinding.ActivityMainBinding
import com.bokchi.umc.flo.databinding.ActivitySongBinding

class SongActivity : AppCompatActivity() {
    private lateinit var songBinding: ActivitySongBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        songBinding = ActivitySongBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(songBinding.root)

        songBinding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            intent.putExtra("title", songBinding.tvTitle.text)

            startActivity(intent)
        }
    }
}