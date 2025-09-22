package ddwu.com.mobile.flo_week2

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ddwu.com.mobile.flo_week2.databinding.ActivitySongBinding

class SongActivity : AppCompatActivity() {
    lateinit var binding: ActivitySongBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val songTitle = intent.getStringExtra("title")
        val songSinger = intent.getStringExtra("singer")
        binding.songMusicTitleTv.text = songTitle
        binding.songSingerNameTv.text = songSinger

        binding.songDownIb.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("title", binding.songMusicTitleTv.text.toString())
            resultIntent.putExtra("singer", binding.songSingerNameTv.text.toString())
            setResult(RESULT_OK, resultIntent)
            finish()
        }

    }
}