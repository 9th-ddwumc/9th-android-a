package com.example.androidA

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidA.databinding.ItemSongBinding

class SongRVAdapter(private val song: ArrayList<Song>) : RecyclerView.Adapter<SongRVAdapter.ViewHolder>() {

    interface MyItemClickListener{
        fun onRemoveSong(position: Int)
    }

    private lateinit var mItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }

    fun removeItem(position: Int){
        song.removeAt(position)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SongRVAdapter.ViewHolder {
        val binding: ItemSongBinding = ItemSongBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SongRVAdapter.ViewHolder, position: Int) {
        holder.bind(song[position])

    }

    override fun getItemCount(): Int = song.size


    inner class ViewHolder(val binding: ItemSongBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(song: Song){
            binding.itemSongTitleTv.text = song.title
            binding.itemSongSingerTv.text = song.singer

            val cover = song.coverImg
            if (cover != null) {
                binding.itemSongImgIv.visibility = View.VISIBLE
                binding.itemSongImgIv.setImageResource(cover)
            } else {
                binding.itemSongImgIv.visibility = View.GONE
            }

            if (song.rank != null) {
                binding.itemSongRankTv.visibility = View.VISIBLE
                binding.itemSongRankTv.text = String.format("%02d", song.rank)
            } else {
                binding.itemSongRankTv.visibility = View.GONE
            }
        }
    }
}