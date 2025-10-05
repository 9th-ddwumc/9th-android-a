package com.example.flo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flo.databinding.ItemSongBinding

class SongRVAdapter(
    private val context: Context,
    initial: List<Song>
) : RecyclerView.Adapter<SongRVAdapter.ViewHolder>() {

    private val songs: MutableList<Song> = initial.toMutableList()

    inner class ViewHolder(val binding: ItemSongBinding) : RecyclerView.ViewHolder(binding.root) {
        val title: TextView = binding.itemAlbumSongTitleTv
        val singer: TextView = binding.itemAlbumSongSingerTv
        val number: TextView = binding.itemAlbumSongNumTv
        val play: ImageButton = binding.itemAlbumSongPlayIb
        val more: ImageButton = binding.itemAlbumSongMoreIb
    }

    interface MyItemClickListener {
        fun onRemoveSong(songId: Int)
        fun onPlay(position: Int)
        fun onMore(position: Int)
    }

    private var mItemClickListener: MyItemClickListener? = null
    fun setMyItemClickListener(listener: MyItemClickListener) { mItemClickListener = listener }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = songs[position]

        holder.title.text = song.title
        holder.singer.text = song.singer
        holder.number.text = String.format("%02d", position + 1)

        // play -> onPlay 호출
        holder.play.setOnClickListener {
            val pos = holder.adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                mItemClickListener?.onPlay(pos)
            }
        }

        // […] More -> 삭제
        holder.more.setOnClickListener {
            val pos = holder.adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                val removed = songs.removeAt(pos)
                notifyItemRemoved(pos)
                mItemClickListener?.onRemoveSong(removed.id)
            }
        }

    }

    override fun getItemCount(): Int = songs.size
}