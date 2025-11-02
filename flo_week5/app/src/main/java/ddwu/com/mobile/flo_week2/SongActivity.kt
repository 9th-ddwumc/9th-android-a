package ddwu.com.mobile.flo_week2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ddwu.com.mobile.flo_week2.data.AlbumDao
import ddwu.com.mobile.flo_week2.data.AlbumDto
import ddwu.com.mobile.flo_week2.databinding.ActivitySongBinding

class SongActivity : AppCompatActivity() {
    lateinit var binding: ActivitySongBinding
    lateinit var song: AlbumDto
    lateinit var timer: Timer
    lateinit var albumDao: AlbumDao

    // 재생 목록
    var songs = ArrayList<AlbumDto>()
    var nowPos = 0 // 현재 재생 중인 곡의 인덱스

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

        // 초기화
        initPlayList()
        initSong()

        // previous
        binding.songPreviousIv.setOnClickListener {
            moveSong(-1)
        }

        // next
        binding.songNextIv.setOnClickListener {
            moveSong(+1)
        }

        // quit (SongActivity -> MainActivity)
        binding.songDownIb.setOnClickListener {
            val resultIntent = Intent()

            resultIntent.putExtra("songList", songs)
            resultIntent.putExtra("nowPos", nowPos)

            resultIntent.putExtra("album", songs[nowPos])

            setResult(RESULT_OK, resultIntent)
            finish()
        }

        // play
        binding.songMiniplayerIv.setOnClickListener {
            setPlayerStatus(true)
        }

        binding.songPauseIv.setOnClickListener {
            setPlayerStatus(false)
        }

        //repeat
        binding.songRepeatIv.setOnClickListener {
            setRepeatStatus(true)
        }

        binding.songRepeatActiveIv.setOnClickListener {
            setRepeatStatus(false)
        }

        //random
        binding.songRandomIv.setOnClickListener {
            setRandomStatus(true)
        }

        binding.songRandomActiveIv.setOnClickListener {
            setRandomStatus(false)
        }



    }

    override fun onDestroy() {
        super.onDestroy()
        timer.interrupt()
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

    private fun initPlayList(){
        albumDao = AlbumDao()
        if (songs.isEmpty()) {
            songs = albumDao.getAllAlbums() // 리스트가 비어있을 때만 불러옴
        }
    }

    private fun initSong(){
        // miniPlayer -> SongActivity
        val intent = intent

        if(intent.hasExtra("songList") && intent.hasExtra("nowPos")){
            songs = intent.getSerializableExtra("songList") as ArrayList<AlbumDto>
            nowPos = intent.getIntExtra("nowPos", 0)
        }
        else{
            initPlayList()
        }
        song = songs[nowPos]

        setPlayer(song)
        startTimer()
    }

    private fun setPlayer(song: AlbumDto){
        binding.songMusicTitleTv.text = song.title
        binding.songSingerNameTv.text = song.singer
        binding.songAlbumIv.setImageResource(song.coverImg ?: R.drawable.img_album_exp)
        binding.songStartTimeTv.text = String.format("%02d:%02d", song.second / 60, song.second % 60)
        binding.songEndTimeTv.text = String.format("%02d:%02d", song.playTime / 60, song.playTime % 60)
        binding.songProgressSb.max = song.playTime * 1000
        binding.songProgressSb.progress = song.second * 1000
        setPlayerStatus(song.isPlaying)
    }

    fun setPlayerStatus(isPlaying: Boolean) {
        songs[nowPos].isPlaying = isPlaying

        if(isPlaying) {
            binding.songMiniplayerIv.visibility = View.GONE
            binding.songPauseIv.visibility = View.VISIBLE
        }
        else{
            binding.songMiniplayerIv.visibility = View.VISIBLE
            binding.songPauseIv.visibility = View.GONE
        }
    }

    fun setRepeatStatus(isRepeat: Boolean) {
        if(isRepeat){
            binding.songRepeatActiveIv.visibility = View.VISIBLE
            binding.songRepeatIv.visibility = View.GONE
        }
        else{
            binding.songRepeatActiveIv.visibility = View.GONE
            binding.songRepeatIv.visibility = View.VISIBLE
        }
    }

    fun setRandomStatus(isRandom: Boolean) {
        if(isRandom){
            binding.songRandomActiveIv.visibility = View.VISIBLE
            binding.songRandomIv.visibility = View.GONE
        }
        else{
            binding.songRandomActiveIv.visibility = View.GONE
            binding.songRandomIv.visibility = View.VISIBLE
        }
    }

    private fun startTimer(){
        timer = Timer(songs[nowPos])
        timer.start()
    }

    inner class Timer(private val song: AlbumDto) : Thread() {
        // Timer 내부에서 사용할 시간 변수들
        private var second: Int = song.second // song의 현재 시간으로 초기화
        private var mills: Int = song.second * 1000 // song의 현재 시간을 밀리초로 변환하여 초기화

        override fun run() {
            super.run()
            try{
                while(true) {
                    // 노래가 끝났는지 확인
                    if(mills >= song.playTime * 1000){
                        runOnUiThread {
                            // 노래가 끝났을 때 UI 최종
                            binding.songStartTimeTv.text = String.format("%02d:%02d", song.playTime / 60, song.playTime % 60)
                            binding.songProgressSb.progress = song.playTime * 1000
                            song.isPlaying = false
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
                            binding.songProgressSb.progress = mills

                            // 1000ms가 될 때마다 시간 업데이트
                            if (mills % 1000 == 0) {
                                second++
                                binding.songStartTimeTv.text = String.format("%02d:%02d", second / 60, second % 60)
                                song.second = second
                            }
                        }
                    }
                }
            }catch(e: InterruptedException){
                Log.d("Song", "Timer가 중단되었습니다. ${e.message}")
            }
        }
    }

}