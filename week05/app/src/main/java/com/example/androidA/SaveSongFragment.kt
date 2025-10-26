package com.example.androidA

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidA.databinding.FragmentSaveSongBinding

class SaveSongFragment : Fragment() {
    lateinit var binding: FragmentSaveSongBinding

    private var saveSongDatas = ArrayList<Song>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSaveSongBinding.inflate(inflater,container,false)

        saveSongDatas.apply {
            add(Song("Butter", "방탄소년단 (BTS)", null, R.drawable.img_album_exp))
            add(Song("Lilac", "아이유 (IU)", null, R.drawable.img_album_exp2))
            add(Song("Next Level", "에스파 (AESPA)", null, R.drawable.img_album_exp3))
            add(Song("Boy with Luv", "방탄소년단 (BTS)", null, R.drawable.img_album_exp4))
            add(Song("BBoom BBoom", "모모랜드 (MOMOLAND)", null, R.drawable.img_album_exp5))
            add(Song("Weekend", "태연 (Tae Yeon)", null, R.drawable.img_album_exp6))
        }

        val saveSongRVAdapter = SaveSongRVAdapter(saveSongDatas)
        binding.saveSongRv.adapter = saveSongRVAdapter
        binding.saveSongRv.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL, false)

        saveSongRVAdapter.setMyItemClickListener(object: SaveSongRVAdapter.MyItemClickListener {
            override fun onRemoveSaveSong(position: Int) {
                saveSongRVAdapter.removeSong(position)
            }
        })

        return binding.root
    }
}