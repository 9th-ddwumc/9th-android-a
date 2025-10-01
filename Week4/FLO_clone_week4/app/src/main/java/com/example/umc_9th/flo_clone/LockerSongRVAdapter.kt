package com.example.umc_9th.flo_clone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.umc_9th.flo_clone.databinding.ItemLockerSongBinding

class LockerSongRVAdapter(private val songList: ArrayList<Song>): RecyclerView.Adapter<LockerSongRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): LockerSongRVAdapter.ViewHolder {
        val binding: ItemLockerSongBinding = ItemLockerSongBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LockerSongRVAdapter.ViewHolder, position: Int) {
        holder.bind(songList[position])

        // 어댑터 삭제 함수
        holder.binding.itemSongMoreIb.setOnClickListener {
            val pos = holder.bindingAdapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                songList.removeAt(pos)
                notifyItemRemoved(pos)
            }
        }
    }

    override fun getItemCount(): Int = songList.size

    inner class ViewHolder(val binding: ItemLockerSongBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(song: Song){
            binding.itemSongCoverIv.setImageResource(song.albumCover)
            binding.itemSongTitleTv.setText(song.title)
            binding.itemSongSingerTv.setText(song.singer)
        }
    }
}