package com.example.umc_9th.flo_clone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_9th.flo_clone.databinding.ItemAlbumBinding

class AlbumRVAdapter(private val albumList: ArrayList<Album>) : RecyclerView.Adapter<AlbumRVAdapter.ViewHolder>() {

    interface MyItemClickListener{
        fun onItemClick(album: Album)
    }

    private lateinit var myItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        myItemClickListener = itemClickListener
    }



    interface PlayButtonClickListener{
        fun onPlayBtnClick(album: Album)
    }

    private  lateinit var playButtonClickListener: PlayButtonClickListener
    fun setPlayButtonClickListener(itemClickListener: PlayButtonClickListener){
        playButtonClickListener = itemClickListener
    }


    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): AlbumRVAdapter.ViewHolder {
        val binding: ItemAlbumBinding = ItemAlbumBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(albumList[position])

        holder.itemView.setOnClickListener { myItemClickListener.onItemClick(albumList[position]) }
    }

    override fun getItemCount(): Int = albumList.size


    inner class ViewHolder(val binding: ItemAlbumBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(album: Album){
            binding.itemAlbumCoverIv.setImageResource(album.albumCover)
            binding.itemAlbumTitleTv.setText(album.title)
            binding.itemAlbumSingerTv.setText(album.singer)

            binding.itemAlbumBtnPlay.setOnClickListener {
                playButtonClickListener.onPlayBtnClick(album)
            }
        }
    }
}