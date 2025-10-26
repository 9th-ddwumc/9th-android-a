package ddwu.com.mobile.flo_week2.data

import java.io.Serializable

data class AlbumDto(
    var title: String?,
    var singer: String?,
    var coverImg: Int?,
    var second: Int = 0,
    val playTime: Int = 0,
    var isPlaying: Boolean = false
) : Serializable
