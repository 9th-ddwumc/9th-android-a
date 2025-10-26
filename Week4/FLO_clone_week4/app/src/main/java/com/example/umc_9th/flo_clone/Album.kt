package com.example.umc_9th.flo_clone

import java.io.Serializable

data class Album (
    val title: String,
    val singer: String,
    val composer: String,
    val albumCover: Int,
    val releaseInfo: String,
    val songList: ArrayList<Song>
): Serializable


data class Song (
    val num: Int,
    val title: String,
    val singer: String,
    val albumCover: Int,
    val lyric1: String,
    val lyric2: String,
    val length: String
): Serializable

