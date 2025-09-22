package ddwu.com.mobile.flo_week2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ddwu.com.mobile.flo_week2.databinding.ActivityMainBinding
import ddwu.com.mobile.flo_week2.fragment.HomeFragment
import ddwu.com.mobile.flo_week2.fragment.LockerFragment

val SONGACTIVITY_CODE = 100
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // HomeFragment 기본으로 띄우기
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragmentContainer, HomeFragment())
                .commit()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.mainBnv.setOnItemSelectedListener { item ->
            when (item.itemId){

                //매인 화면
                R.id.homeFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragmentContainer, HomeFragment())
                        .commit()
                    true
                }

                //둘러보기 화면
//                R.id.lookFragment -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.main_fragmentContainer, LookFragment())
//                        .commit()
//                    true
//                }
//
//                //검색 화면
//                R.id.searchFragment -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.main_fragmentContainer, SearchFragment())
//                        .commit()
//                    true
//                }
//
                //보관함 화면
                R.id.lockerFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragmentContainer, LockerFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }

        // 미니 플레이어 -> SongActivity
        binding.player.setOnClickListener {
            val intent = Intent(this, SongActivity::class.java)
            intent.putExtra("title", binding.songTitle.text.toString())
            intent.putExtra("singer", binding.songSinger.text.toString())
            startActivityForResult(intent, SONGACTIVITY_CODE)
        }

    }

    // 액티비티로부터 응답 받기
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == SONGACTIVITY_CODE){
            when(resultCode) {
                RESULT_OK ->
                {
                    val title = data?.getStringExtra("title")
                    val singer = data?.getStringExtra("singer")
                    Toast.makeText(this, "title: $title, singer: $singer", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}