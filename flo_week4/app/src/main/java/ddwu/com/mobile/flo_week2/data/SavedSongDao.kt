package ddwu.com.mobile.flo_week2.data

import ddwu.com.mobile.flo_week2.R

class SavedSongDao {
    val savedSongs = ArrayList<SavedSongDto>()

    init {
        savedSongs.add(SavedSongDto("LILAC", "아이유", R.drawable.img_album_exp2))
        savedSongs.add(SavedSongDto("In My Bed", "bean", R.drawable.img_album_exp))
        savedSongs.add(SavedSongDto("파아란", "안예은", R.drawable.img_album_exp3))
        savedSongs.add(SavedSongDto("Light Switch", "Charlie Puth", R.drawable.img_album_exp4))
        savedSongs.add(SavedSongDto("LILAC", "아이유", R.drawable.img_album_exp2))
        savedSongs.add(SavedSongDto("In My Bed", "bean", R.drawable.img_album_exp))
        savedSongs.add(SavedSongDto("파아란", "안예은", R.drawable.img_album_exp3))
        savedSongs.add(SavedSongDto("Light Switch", "Charlie Puth", R.drawable.img_album_exp4))
        savedSongs.add(SavedSongDto("LILAC", "아이유", R.drawable.img_album_exp2))
        savedSongs.add(SavedSongDto("In My Bed", "bean", R.drawable.img_album_exp))
        savedSongs.add(SavedSongDto("파아란", "안예은", R.drawable.img_album_exp3))
        savedSongs.add(SavedSongDto("Light Switch", "Charlie Puth", R.drawable.img_album_exp4))
    }

    fun removeSong(savedSong: SavedSongDto) {
        savedSongs.remove(savedSong)
    }
}