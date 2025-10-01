package com.example.umc_9th.flo_clone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_9th.flo_clone.databinding.ItemAlbumSongBinding

class AlbumSongRVAdapter(private val albumSongList: ArrayList<Song>): RecyclerView.Adapter<AlbumSongRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): AlbumSongRVAdapter.ViewHolder {
        val binding: ItemAlbumSongBinding = ItemAlbumSongBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(albumSongList[position])
    }

    override fun getItemCount(): Int = albumSongList.size

    inner class ViewHolder(val binding: ItemAlbumSongBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(song: Song){
            binding.itemAlbumSongNumTv.setText("%02d".format(song.num))
            binding.itemAlbumSongTitleTv.setText(song.title)
            binding.itemAlbumSongSingerTv.setText(song.singer)
        }
    }
}