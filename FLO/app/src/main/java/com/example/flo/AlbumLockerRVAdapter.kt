package com.example.flo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flo.databinding.ItemLockerAlbumBinding

class AlbumLockerRVAdapter : RecyclerView.Adapter<AlbumLockerRVAdapter.ViewHolder>() {

    private val albums = mutableListOf<Album>()

    interface MyItemClickListener {
        fun onRemoveSong(position: Int)
        fun onClick(position: Int)
    }
    private var mItemClickListener: MyItemClickListener? = null
    fun setMyItemClickListener(listener: MyItemClickListener) { mItemClickListener = listener }

    inner class ViewHolder(val binding: ItemLockerAlbumBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(album: Album) {
            album.coverImg?.let { binding.itemAlbumImgIv.setImageResource(it) }
                ?: binding.itemAlbumImgIv.setImageDrawable(null)

            binding.itemAlbumTitleTv.text = album.title
            binding.itemAlbumSingerTv.text = album.singer

            // ★ here
            binding.root.setOnClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    mItemClickListener?.onClick(pos)
                }
            }
            binding.root.setOnLongClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    mItemClickListener?.onRemoveSong(pos)
                }
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLockerAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(albums[position])
    }

    override fun getItemCount(): Int = albums.size

    fun addAlbums(list: List<Album>) {
        albums.clear()
        albums.addAll(list)
        notifyDataSetChanged()
    }

    fun removeSong(position: Int) {
        if (position in albums.indices) {
            albums.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}