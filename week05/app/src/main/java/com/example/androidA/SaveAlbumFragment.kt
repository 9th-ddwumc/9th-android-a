package com.example.androidA

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidA.databinding.FragmentSaveAlbumBinding

class SaveAlbumFragment : Fragment() {
    lateinit var binding: FragmentSaveAlbumBinding

    private var saveAlbumDatas = ArrayList<Album>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSaveAlbumBinding.inflate(inflater,container,false)

        saveAlbumDatas.apply {
            add(Album("Butter", "방탄소년단 (BTS)", R.drawable.img_album_exp, "2021.03.25 | 정규 | 댄스 팝"))
            add(Album("Lilac", "아이유 (IU)", R.drawable.img_album_exp2, "2021.04.26 | 정규 | 댄스 팝"))
            add(Album("Next Level", "에스파 (AESPA)", R.drawable.img_album_exp3, "2022.05.17 | 정규 | 댄스 팝"))
            add(Album("Boy with Luv", "방탄소년단 (BTS)", R.drawable.img_album_exp4, "2023.07.06 | 정규 | 댄스 팝"))
            add(Album("BBoom BBoom", "모모랜드 (MOMOLAND)", R.drawable.img_album_exp5, "2025.09.11 | 정규 | 댄스 팝"))
            add(Album("Weekend", "태연 (Tae Yeon)", R.drawable.img_album_exp6, "2024.12.31 | 정규 | 댄스 팝"))
        }

        val saveAlbumRVAdapter = SaveAlbumRVAdapter(saveAlbumDatas)
        binding.saveAlbumRv.adapter = saveAlbumRVAdapter
        binding.saveAlbumRv.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL, false)

        saveAlbumRVAdapter.setMyItemClickListener(object: SaveAlbumRVAdapter.MyItemClickListener {
            override fun onRemoveSaveAlbum(position: Int) {
                saveAlbumRVAdapter.removeAlbum(position)
            }
        })

        return binding.root
    }
}