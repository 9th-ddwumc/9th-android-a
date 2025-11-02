package ddwu.com.mobile.flo_week2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ddwu.com.mobile.flo_week2.data.TrackDto
import ddwu.com.mobile.flo_week2.databinding.ListTrackBinding

class TrackAdapter(val tracks: ArrayList<TrackDto>) : RecyclerView.Adapter<TrackAdapter.TrackViewHolder>() {

    override fun getItemCount(): Int = tracks.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrackViewHolder {
        val trackBinding = ListTrackBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrackViewHolder(trackBinding)
    }

    override fun onBindViewHolder(
        holder: TrackViewHolder,
        position: Int
    ) {
        holder.trackBinding.songListOrderTv.text = tracks[position].id
        holder.trackBinding.songMusicTitleTv.text = tracks[position].title
        holder.trackBinding.songSingerNameTv.text = tracks[position].singer
    }

    inner class TrackViewHolder(val trackBinding: ListTrackBinding) : RecyclerView.ViewHolder(trackBinding.root)
}