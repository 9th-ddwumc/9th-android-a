package com.example.androidA

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidA.databinding.FragmentSongBinding
class SongFragment : Fragment() {
    lateinit var binding: FragmentSongBinding

    private var songDatas = ArrayList<Song>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSongBinding.inflate(inflater,container,false)

        songDatas.apply {
            add(Song("Lilac", "아이유 (IU)", 1, null))
            add(Song("Flu", "아이유 (IU)", 2, null))
            add(Song("Coin", "아이유 (IU)", 3, null))
            add(Song("봄 안녕 봄", "아이유 (IU)", 4, null))
            add(Song("Celebrity", "아이유 (IU)", 5, null))
            add(Song("돌림노래 (Feat. DEAN)", "아이유 (IU)", 6, null))
        }

        val songRVAdapter = SongRVAdapter(songDatas)
        binding.songRv.adapter = songRVAdapter
        binding.songRv.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL, false)

        songRVAdapter.setMyItemClickListener(object: SongRVAdapter.MyItemClickListener {
            override fun onRemoveSong(position: Int) {
                songRVAdapter.removeItem(position)
            }
        })

        binding.songMixoffTg.setOnClickListener {
            setPlayerStatus(false)
        }
        binding.songMixonTg.setOnClickListener {
            setPlayerStatus(true)
        }

        return binding.root
    }

    fun setPlayerStatus(isPlaying : Boolean) {
        if(isPlaying) {
            binding.songMixoffTg.visibility = View.VISIBLE
            binding.songMixonTg.visibility = View.GONE
        }
        else {
            binding.songMixoffTg.visibility = View.GONE
            binding.songMixonTg.visibility = View.VISIBLE
        }
    }
}