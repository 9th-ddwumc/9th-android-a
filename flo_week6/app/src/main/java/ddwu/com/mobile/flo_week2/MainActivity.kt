package ddwu.com.mobile.flo_week2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ddwu.com.mobile.flo_week2.data.AlbumDao
import ddwu.com.mobile.flo_week2.data.AlbumDto
import ddwu.com.mobile.flo_week2.databinding.ActivityMainBinding
import ddwu.com.mobile.flo_week2.fragment.HomeFragment
import ddwu.com.mobile.flo_week2.fragment.LockerFragment

val SONGACTIVITY_CODE = 100
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var song: AlbumDto
    lateinit var timer: Timer
    lateinit var albumDao: AlbumDao

    var songs = ArrayList<AlbumDto>()
    var nowPos = 0;

    var currentSong: AlbumDto? = null

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

        initPlayList()
        initSong()

        binding.btnPrevious.setOnClickListener {
            moveSong(-1)
        }
        binding.btnNext.setOnClickListener {
            moveSong(1)
        }
        binding.miniplayerIv.setOnClickListener {
            setPlayerStatus(true)
        }
        binding.pauseIv.setOnClickListener {
            setPlayerStatus(false)
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
            if (::timer.isInitialized) {
                timer.interrupt()
            }

            val currentSong = songs[nowPos]
            val intent = Intent(this, SongActivity::class.java).apply {
                putExtra("album", currentSong)
                putExtra("songList", songs)
                putExtra("nowPos", nowPos)
            }
            startActivityForResult(intent, SONGACTIVITY_CODE)
        }
    }

    // SeekBar 및 Timer
    private fun initPlayList(){
        albumDao = AlbumDao()
        songs = albumDao.getAllAlbums()
    }

    private fun initSong(){
        //SongActivity -> miniPlayer
        if(intent.hasExtra("album")){
            song = intent.getSerializableExtra("album") as AlbumDto
            if(song != null){
                nowPos = songs.indexOfFirst{it.title == song.title}
                if(nowPos == -1) nowPos = 0
            }
        }
        setPlayer(songs[nowPos])
        startTimer()
    }

    private fun setPlayer(song: AlbumDto){
        binding.songTitle.text = song.title
        binding.songSinger.text = song.singer
        binding.mainSongProgressSb.max = song.playTime * 1000
        binding.mainSongProgressSb.progress = song.second * 1000
        setPlayerStatus(song.isPlaying)
    }

    fun setPlayerStatus(isPlaying: Boolean) {
        songs[nowPos].isPlaying = isPlaying

        if(isPlaying) {
            binding.miniplayerIv.visibility = View.GONE
            binding.pauseIv.visibility = View.VISIBLE
        }
        else{
            binding.miniplayerIv.visibility = View.VISIBLE
            binding.pauseIv.visibility = View.GONE
        }
    }

    private fun startTimer(){
        timer = Timer(songs[nowPos])
        timer.start()
    }

    inner class Timer(private val song: AlbumDto) : Thread() {
        // Timer 내부에서 사용할 시간 변수들
        private var second: Int = song.second
        private var mills: Int = song.second * 1000

        override fun run() {
            super.run()
            try{
                while(true) {
                    // 노래가 끝났는지 확인
                    if(mills >= song.playTime * 1000){
                        runOnUiThread {
                            binding.mainSongProgressSb.progress = song.playTime * 1000
                            setPlayerStatus(false)
                        }
                        break
                    }

                    // song 객체의 isPlaying 상태 확인
                    if(song.isPlaying){
                        sleep(50)
                        mills += 50

                        // UI 업데이트
                        runOnUiThread {
                            binding.mainSongProgressSb.progress = mills

                            // 1000ms가 될 때마다 시간 업데이트
                            if (mills % 1000 == 0) {
                                second++
                                song.second = second
                            }
                        }
                    }
                }
            }catch(e: InterruptedException){
                Log.d("Main", "Timer가 중단되었습니다. ${e.message}")
            }
        }
    }

    private fun moveSong(direction: Int){
        timer.interrupt()

        nowPos += direction

        if (nowPos < 0) {
            nowPos = songs.size - 1
        }
        if (nowPos >= songs.size) {
            nowPos = 0
        }

        setPlayer(songs[nowPos])
        startTimer()
    }

    // 앨범 play 버튼 클릭 시
    fun onPlayButtonClicked(album: AlbumDto) {
        var newPos = songs.indexOfFirst { it.title == album.title && it.singer == album.singer }

        if (newPos == -1) {
            songs.add(album.copy())
            newPos = songs.size - 1
        }

        nowPos = newPos

        songs[nowPos].second = 0
        songs[nowPos].isPlaying = true

        setPlayer(songs[nowPos])

        startTimer()
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
                    if(data != null) {
                        if(data.hasExtra("songList") && data.hasExtra("nowPos")){
                            songs = data.getSerializableExtra("songList") as ArrayList<AlbumDto>
                            nowPos = data.getIntExtra("nowPos", 0)
                        }

                        if(data.hasExtra("album")){
                            val updatedSong = data.getSerializableExtra("album") as AlbumDto

                            if(::timer.isInitialized){
                                timer.interrupt()
                            }

                            setPlayer(updatedSong)
                            startTimer()
                        }
                    }
                }
            }
        }
    }
}