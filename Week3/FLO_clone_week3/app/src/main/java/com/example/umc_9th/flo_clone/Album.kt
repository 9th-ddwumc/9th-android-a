package com.example.umc_9th.flo_clone

import java.io.Serializable

data class Album (
    val title: String,
    val composer: String,
    val songTitle: String,
    val singer: String,
    val albumCover: Int,
    val releaseInfo: String,
    val lyric1: String,
    val lyric2: String,
    val length: String
): Serializable

