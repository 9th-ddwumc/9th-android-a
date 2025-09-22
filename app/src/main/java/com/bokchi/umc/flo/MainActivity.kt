package com.bokchi.umc.flo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bokchi.umc.flo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainbinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        mainbinding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(mainbinding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(mainbinding.mainBnv.id, HomeFragment())
            .commit()

        val title = intent.getStringExtra("title")
        if (title != null) {
            Toast.makeText(this, title, Toast.LENGTH_SHORT).show()
        }

        mainbinding.miniPlayer.setOnClickListener {
            val intent = Intent(this,SongActivity::class.java)

            intent.putExtra("title", mainbinding.miniSongTitle.text)
            intent.putExtra("artist", mainbinding.miniSongArtist.text)

            startActivity(intent)
        }
    }
}