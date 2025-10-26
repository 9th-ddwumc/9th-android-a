package ddwu.com.mobile.flo_week2.adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ddwu.com.mobile.flo_week2.R
import ddwu.com.mobile.flo_week2.data.AlbumDto
import ddwu.com.mobile.flo_week2.databinding.ListAlbumBinding

class AlbumAdapter (val albums: ArrayList<AlbumDto>) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {
    override fun getItemCount(): Int = albums.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumViewHolder {
        val albumBinding = ListAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumViewHolder(albumBinding)
    }

    override fun onBindViewHolder(
        holder: AlbumViewHolder,
        position: Int
    ) {
        holder.albumBinding.todayAlbumImage1.setImageResource(albums[position].coverImg?: R.drawable.img_album_exp)
        holder.albumBinding.todayReleaseSongTitle.text = albums[position].title
        holder.albumBinding.todayReleaseSongSinger.text = albums[position].singer
    }

    interface OnItemClickListener {
        fun onItemClick(album: AlbumDto)
    }

    interface OnPlayButtonClickListener {
        fun onPlayButtonClick(album: AlbumDto)
    }

    lateinit var itemClickListener: OnItemClickListener
    lateinit var playButtonClickListener: OnPlayButtonClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }
    fun setOnPlayButtonClickListener(listener: OnPlayButtonClickListener) {
        playButtonClickListener = listener
    }

    inner class AlbumViewHolder(val albumBinding: ListAlbumBinding): RecyclerView.ViewHolder(albumBinding.root){
        init {
            albumBinding.todayAlbumImage1.setOnClickListener {
                itemClickListener.onItemClick(albums[adapterPosition])
            }
            albumBinding.btnPlayer.setOnClickListener {
                playButtonClickListener.onPlayButtonClick(albums[adapterPosition])
            }
        }
    }
}