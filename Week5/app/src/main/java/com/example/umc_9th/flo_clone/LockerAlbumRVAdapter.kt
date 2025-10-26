package com.example.umc_9th.flo_clone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Visibility
import androidx.viewbinding.ViewBinding
import com.example.umc_9th.flo_clone.databinding.ItemLockerAlbumBinding

class LockerAlbumRVAdapter(private val albumData: ArrayList<Album>): RecyclerView.Adapter<LockerAlbumRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): LockerAlbumRVAdapter.ViewHolder {
        val binding: ItemLockerAlbumBinding = ItemLockerAlbumBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LockerAlbumRVAdapter.ViewHolder, position: Int) {
        holder.bind(albumData[position])

        // 어댑터 삭제 함수
        holder.binding.itemLockerAlbumMoreIb.setOnClickListener {
            val pos = holder.bindingAdapterPosition
            if (pos != RecyclerView.NO_POSITION){
                albumData.removeAt(pos)
                notifyItemRemoved(pos)
            }
        }
    }

    override fun getItemCount(): Int = albumData.size



    inner class ViewHolder(val binding: ItemLockerAlbumBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(album: Album){
            binding.itemLockerAlbumCoverIv.setImageResource(album.albumCover)
            binding.itemLockerAlbumTitleTv.setText(album.title)
            binding.itemLockerAlbumSingerTv.setText(album.singer)
            binding.itemLockerAlbumInfo.setText(album.releaseInfo)

            binding.itemLockerAlbumPlayIb.setOnClickListener {
                binding.itemLockerAlbumPlayIb.visibility = View.GONE
                binding.itemLockerAlbumPauseIb.visibility = View.VISIBLE
            }

            binding.itemLockerAlbumPauseIb.setOnClickListener {
                binding.itemLockerAlbumPauseIb.visibility = View.GONE
                binding.itemLockerAlbumPlayIb.visibility = View.VISIBLE
            }
        }
    }
}