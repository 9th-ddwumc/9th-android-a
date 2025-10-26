package com.example.androidA

import java.util.ArrayList

data class Album(
    var title: String? = "",
    var singer: String? = "",
    var coverImg: Int? = null,
    var detail: String? = null,
    var isPlaying: Boolean = false,
    var songs: ArrayList<Song>? = null
)
