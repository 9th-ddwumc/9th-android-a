package com.example.androidA

//제목, 가수, 사진,재생시간,현재 재생시간, isplaying(재생 되고 있는지)
data class Song(
    var title: String = "",
    var singer: String = "",
    val rank: Int? = null,
    var coverImg: Int? = null
)