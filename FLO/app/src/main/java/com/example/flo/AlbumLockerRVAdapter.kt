package com.example.flo

import android.util.SparseBooleanArray

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flo.databinding.ItemLockerAlbumBinding

class AlbumLockerRVAdapter : RecyclerView.Adapter<AlbumLockerRVAdapter.ViewHolder>() {

    private val albums = mutableListOf<Album>()
    private val playingStates = SparseBooleanArray() // position -> isPlaying

    interface MyItemClickListener {
        fun onRemoveAlbum(position: Int)
        fun onClick(position: Int)
    }
    private var mItemClickListener: MyItemClickListener? = null
    fun setMyItemClickListener(listener: MyItemClickListener) { mItemClickListener = listener }

    inner class ViewHolder(val binding: ItemLockerAlbumBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(album: Album, position: Int) {
            album.coverImg?.let { binding.itemAlbumImgIv.setImageResource(it) }
                ?: binding.itemAlbumImgIv.setImageDrawable(null)

            binding.itemAlbumTitleTv.text = album.title
            binding.itemAlbumSingerTv.text = album.singer

            // 현재 상태에 맞춰 아이콘 세팅 (개별 아이템 토글)
            val isPlaying = playingStates.get(position, false)
            binding.itemAlbumPlayIv.setImageResource(
                if (isPlaying) R.drawable.btn_miniplay_mvpause else R.drawable.btn_miniplay_mvplay
            )

            // […] 버튼 클릭 시 삭제
            binding.itemAlbumMoreIv.setOnClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    mItemClickListener?.onRemoveAlbum(pos)
                }
            }

            // ▶︎ 개별 플레이 아이콘 토글
            binding.itemAlbumPlayIv.setOnClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    val newState = !playingStates.get(pos, false)
                    playingStates.put(pos, newState)
                    notifyItemChanged(pos)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLockerAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(albums[position], position)
    }

    override fun getItemCount(): Int = albums.size

    fun addAlbums(list: List<Album>) {
        albums.clear()
        playingStates.clear()
        albums.addAll(list)
        notifyDataSetChanged()
    }

    fun removeAlbum(position: Int) {
        if (position in albums.indices) {
            albums.removeAt(position)
            playingStates.clear() // 간단히 초기화 (필요 시 위치 보정 로직으로 변경 가능)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, albums.size - position)
        }
    }
}