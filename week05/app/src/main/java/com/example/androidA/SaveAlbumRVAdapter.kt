package com.example.androidA

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidA.databinding.ItemSaveAlbumBinding

class SaveAlbumRVAdapter(private val albums: ArrayList<Album>) :
    RecyclerView.Adapter<SaveAlbumRVAdapter.ViewHolder>() {
    interface MyItemClickListener{
        fun onRemoveSaveAlbum(position: Int)
    }
    private lateinit var mItemClickListener : MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SaveAlbumRVAdapter.ViewHolder {
        val binding: ItemSaveAlbumBinding = ItemSaveAlbumBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SaveAlbumRVAdapter.ViewHolder, position: Int) {
        holder.bind(albums[position])
        holder.binding.itemAlbumMoreIv.setOnClickListener {
            mItemClickListener.onRemoveSaveAlbum(position)
        }

        holder.binding.itemAlbumPlayIv.setOnClickListener {
            val album = albums[position]
            album.isPlaying = !album.isPlaying
            notifyItemChanged(position)

            (holder.itemView.context as? MainActivity)?.updateMiniPlayer(
                album.title ?: "",
                album.singer ?: "",
                album.isPlaying
            )
        }
    }

    override fun getItemCount(): Int = albums.size

    @SuppressLint("NotifyDataSetChanged")
    fun addAlbums(albums: ArrayList<Album>) {
        this.albums.clear()
        this.albums.addAll(albums)

        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeAlbum(position: Int){
        albums.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, albums.size)
    }

    inner class ViewHolder(val binding: ItemSaveAlbumBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(album: Album){
            binding.itemAlbumImgIv.setImageResource(album.coverImg!!)
            binding.itemAlbumTitleTv.text = album.title
            binding.itemAlbumSingerTv.text = album.singer
            binding.itemAlbumMusicTitleInfoTv.text = album.detail

            if (album.isPlaying) {
                binding.itemAlbumPlayIv.setImageResource(R.drawable.btn_miniplay_pause)
            } else {
                binding.itemAlbumPlayIv.setImageResource(R.drawable.btn_player_play)
            }
        }
    }
}