package ddwu.com.mobile.flo_week2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ddwu.com.mobile.flo_week2.R
import ddwu.com.mobile.flo_week2.data.SavedSongDto
import ddwu.com.mobile.flo_week2.databinding.ListSavedSongBinding

class SavedSongAdapter(val savedSongs: ArrayList<SavedSongDto>) :
    RecyclerView.Adapter<SavedSongAdapter.SavedSongViewHolder>() {

    override fun getItemCount(): Int = savedSongs.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SavedSongViewHolder {
        val savedSongBinding = ListSavedSongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SavedSongViewHolder(savedSongBinding)
    }

    override fun onBindViewHolder(
        holder: SavedSongViewHolder,
        position: Int
    ) {
        holder.savedSongBinding.songMusicTitleTv.text = savedSongs[position].title
        holder.savedSongBinding.songSingerNameTv.text = savedSongs[position].singer
        holder.savedSongBinding.songIv.setImageResource(savedSongs[position].coverImg)
        holder.savedSongBinding.releaseDtTv.text = savedSongs[position].releaseDt
        holder.savedSongBinding.typeTv.text = savedSongs[position].albumType
        holder.savedSongBinding.categoryTv.text = savedSongs[position].category

        if (savedSongs[position].isPlaying) {
            holder.savedSongBinding.songPlayIv.visibility = View.GONE
            holder.savedSongBinding.songPauseIv.visibility = View.VISIBLE
        }
        else {
            holder.savedSongBinding.songPlayIv.visibility = View.VISIBLE
            holder.savedSongBinding.songPauseIv.visibility = View.GONE
        }
        holder.savedSongBinding.songPlayIv.setOnClickListener {
            playButtonClickListener?.onPlayButtonClick(position)
        }
        holder.savedSongBinding.songPauseIv.setOnClickListener {
            playButtonClickListener?.onPlayButtonClick(position)
        }

    }

    interface onPlayButtonClickListener {
        fun onPlayButtonClick(position: Int)
    }

    lateinit var playButtonClickListener: onPlayButtonClickListener

    fun setOnPlayButtonClickListener(listener: onPlayButtonClickListener) {
        playButtonClickListener = listener
    }
    inner class SavedSongViewHolder(val savedSongBinding: ListSavedSongBinding) :
        RecyclerView.ViewHolder(savedSongBinding.root){
            init{
                savedSongBinding.songMoreIv.setOnClickListener {
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        savedSongs.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position, savedSongs.size - position)
                    }
                }
            }
        }
}