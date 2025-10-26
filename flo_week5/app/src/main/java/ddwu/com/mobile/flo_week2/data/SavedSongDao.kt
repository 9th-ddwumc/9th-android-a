package ddwu.com.mobile.flo_week2.data

import ddwu.com.mobile.flo_week2.R

class SavedSongDao {
    val savedSongs = ArrayList<SavedSongDto>()

    init {
        savedSongs.add(SavedSongDto("LILAC", "아이유", R.drawable.img_album_exp2,"2025.01.01", "정규", "KPOP", false))
        savedSongs.add(SavedSongDto("In My Bed", "bean", R.drawable.img_album_exp, "2025.01.02", "미니", "POP", false))
        savedSongs.add(SavedSongDto("파아란", "안예은", R.drawable.img_album_exp3, "2025.01.03", "정규", "KPOP", false))
        savedSongs.add(SavedSongDto("Light Switch", "Charlie Puth", R.drawable.img_album_exp4,"2025.01.04", "정규", "POP", false))
        savedSongs.add(SavedSongDto("LILAC", "아이유", R.drawable.img_album_exp2, "2025.01.01", "정규", "KPOP", false))
        savedSongs.add(SavedSongDto("In My Bed", "bean", R.drawable.img_album_exp, "2025.01.02", "미니", "POP", false))
        savedSongs.add(SavedSongDto("파아란", "안예은", R.drawable.img_album_exp3, "2025.01.03", "정규", "KPOP", false))
        savedSongs.add(SavedSongDto("Light Switch", "Charlie Puth", R.drawable.img_album_exp4, "2025.01.04", "정규", "POP", false))
        savedSongs.add(SavedSongDto("LILAC", "아이유", R.drawable.img_album_exp2, "2025.01.01", "정규", "KPOP", false))
        savedSongs.add(SavedSongDto("In My Bed", "bean", R.drawable.img_album_exp, "2025.01.02", "미니", "POP", false))
        savedSongs.add(SavedSongDto("파아란", "안예은", R.drawable.img_album_exp3, "2025.01.03", "정규", "KPOP", false))
        savedSongs.add(SavedSongDto("Light Switch", "Charlie Puth", R.drawable.img_album_exp4,"2025.01.04", "정규", "POP", false))
    }

    fun removeSong(savedSong: SavedSongDto) {
        savedSongs.remove(savedSong)
    }
}