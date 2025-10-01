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

    var albumData: ArrayList<Album> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 데이터
        albumData.apply {
            add(Album("Butter", "방탄소년단 (BTS)", "Jenna Andrews and others",
                R.drawable.img_album_exp, "2021.05.21 | 싱글 | 댄스 팝",
                arrayListOf(
                    Song(1, "Butter", "방탄소년단 (BTS)", R.drawable.img_album_exp,"Smooth like butter",   "like a criminal undercover", "02:44"),
                    Song(2, "Butter (Hotter Remix)", "방탄소년단 (BTS)", R.drawable.img_album_exp,"Smooth like butter",  "like a criminal undercover", "02:47"),
                    Song(3, "Butter (Sweeter Remix)", "방탄소년단 (BTS)", R.drawable.img_album_exp, "Smooth like butter", "like a criminal undercover", "02:4"),
                    Song(4, "Butter (Cooler Remix)", "방탄소년단 (BTS)", R.drawable.img_album_exp,"Smooth like butter",  "like a criminal undercover", "02:41"),
                    Song(5, "Butter (Instrumental)", "방탄소년단 (BTS)", R.drawable.img_album_exp, "", "", "02:42")

                )
            ))
            add(Album("LILAC", "아이유 (IU)", "imsuho and others",
                R.drawable.img_album_exp2, "2021.03.25 | 정규 | 댄스 팝",
                arrayListOf(
                    Song(1, "라일락", "아이유 (IU)", R.drawable.img_album_exp2,"나리는 꽃가루에", "눈이 따끔해 아야",  "03:39"),
                    Song(2, "Flu", "아이유 (IU)", R.drawable.img_album_exp2,"Doc I'm feeling bad", "미열이 흐르고 또 어질어질해",  "03:08"),
                    Song(3, "Coin", "아이유 (IU)", R.drawable.img_album_exp2,"강자에게 더 세게 I love gamble", "과감할수록 신세계 On my table",  "03:13"),
                    Song(4, "봄 안녕 봄", "아이유 (IU)", R.drawable.img_album_exp2,"아프던 너의 이름도", "이제는 미련이 아냐",  "05:24"),
                    Song(5, "Celebrity", "아이유 (IU)", R.drawable.img_album_exp2,"세상의 모서리", "구부정하게 커버린",  "03:15"),
                    Song(6, "돌림노래 (Feat. DEAN)", "아이유 (IU)", R.drawable.img_album_exp2,"죄책감 없이 밤늦게 전화해도 돼", "(그 다음 날) 기억도 못 할 헛소릴 뱉어도 돼",  "03:09"),
                    Song(7, "빈 컵 (Empty Cup)", "아이유 (IU)", R.drawable.img_album_exp2,"창백한 눈으로 날 바라보는", "넌 변함 없이 빛나",  "02:19"),
                    Song(8, "아이와 나의 바다", "아이유 (IU)", R.drawable.img_album_exp2,"그러나 시간이 지나도", "아물지 않는 일들이 있지",  "05:16"),
                    Song(9, "어푸 (Ah puh)", "아이유 (IU)", R.drawable.img_album_exp2,"I'm such a good surfer", "가라앉지 않기",  "03:20"),
                    Song(10, "에필로그", "아이유 (IU)", R.drawable.img_album_exp2,"나를 알게 되어서 기뻤는지", "나를 사랑해서 좋았었는지",  "03:49"),
                )
            ))
            add(Album("Next Level", "aespa", "Mario Marchett and others",
                R.drawable.img_album_exp3, "2021.05.17 | 싱글 | 댄스 팝",
                arrayListOf(
                    Song(1, "Next Level", "aespa", R.drawable.img_album_exp3, "I'm on the Next Level Yeah", "절대적 룰을 지켜", "03:41")
                )
            ))
            add(Album("PERSONA", "방탄소년단 (BTS)", "Pdogg and others",
                R.drawable.img_album_exp4, "2019.04.12 | 미니 | 알앤비 힙합",
                arrayListOf(
                    Song(1, "Intro : Persona", "방탄소년단 (BTS)", R.drawable.img_album_exp4, "나는 누구인가 평생 물어온 질문", "아마 평생 정답은 찾지 못할 그 질문", "02:51"),
                    Song(2, "작은 것들을 위한 시 (Boy With Luv)", "방탄소년단 (BTS)", R.drawable.img_album_exp4, "모든 게 궁금해 How's your day", "Oh tell me", "03:49"),
                    Song(3, "소우주 (Mikrokosmos)", "방탄소년단 (BTS)", R.drawable.img_album_exp4, "반짝이는 별빛들", "깜빡이는 불 켜진 건물", "03:44"),
                    Song(4, "Make It Right", "방탄소년단 (BTS)", R.drawable.img_album_exp4, "내가 날 눈치챘던 순간", "떠나야만 했어", "03:46"),
                    Song(5, "HOME", "방탄소년단 (BTS)", R.drawable.img_album_exp4, "미칠듯한 설레임에", "인사조차 못했어", "03:54"),
                    Song(6, "Jamais Vu", "방탄소년단 (BTS)", R.drawable.img_album_exp4, "또 져버린 것 같아", "넌 화가 나 보여", "03:47"),
                    Song(7, "Dionysus", "방탄소년단 (BTS)", R.drawable.img_album_exp4, "쭉 들이켜", "술잔 (Sippin') 팔짱 (Tippin')", "04:09")
                    )
            ))
            add(Album("GREAT!", "모모랜드(MOMOLAND)", "신사동호랭이 and others",
                R.drawable.img_album_exp5, "2018.01.03 | 미니 | 댄스 팝",
                arrayListOf(
                    Song(1, "뿜뿜", "모모랜드(MOMOLAND)", R.drawable.img_album_exp5, "설레어 너와 나의 랑데뷰 (랑데뷰)", "내 마음을 들었다 놨다 해 맘대루 (맘대루)", "03:28"),
                    Song(2, "궁금해", "모모랜드(MOMOLAND)", R.drawable.img_album_exp5, "널 보면 설레는 이유", "너 땜에 없던 버릇이 하나 생겼어", "03:16"),
                    Song(3, "Same Same", "모모랜드(MOMOLAND)", R.drawable.img_album_exp5, "딱 너만큼만", "딱 나만큼만", "03:18"),
                    Song(4, "Fly", "모모랜드(MOMOLAND)", R.drawable.img_album_exp5, "한걸음 다가와 속삭여 줄래", "항상 널 비추는 빛이 되줄게 aye", "03:05"),
                    Song(5, "뿜뿜 (inst.)", "모모랜드(MOMOLAND)", R.drawable.img_album_exp5, "", "", "03:28"),
                    Song(6, "어마어마해 (EDM ver.) (inst.)", "모모랜드(MOMOLAND)", R.drawable.img_album_exp5, "", "", "03:23"),
                )
            ))
            add(Album("Weekend", "태연 (TAEYEON)", "ROSEINPEACE and others",
                R.drawable.img_album_exp6, "2021.07.06 | 싱글 | 댄스 팝",
                arrayListOf(
                    Song(1, "Weekend", "태연 (TAEYEON)", R.drawable.img_album_exp6, "가장 가까운 바다", "혼자만의 영화관", "03:53"),
                )
            ))
        }

        binding.mainPlayingTitleTv.setText(albumData[0].songList[0].title)
        binding.mainPlayingSingerTv.setText(albumData[0].singer)

        binding.mainPlayerCl.setOnClickListener {
            val intent = Intent(this, SongActivity::class.java).apply {
                putExtra("albumData", albumData)
            }
            startActivity(intent)
        }

        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        val homeFragment = HomeFragment().apply {
            arguments = Bundle().apply {
                putSerializable("albumData", albumData)
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
                            putSerializable("albumData", albumData)
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
                    val lockerFragment = LockerFragment().apply {
                        arguments = Bundle().apply {
                            putSerializable("albumData", albumData)
                        }
                    }

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, lockerFragment)
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}