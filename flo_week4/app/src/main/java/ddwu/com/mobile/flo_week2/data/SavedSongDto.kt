package ddwu.com.mobile.flo_week2.data

import java.io.Serializable

class SavedSongDto(
    val title: String,
    val singer: String,
    val coverImg: Int,
    var isPlaying: Boolean = false
) : Serializable