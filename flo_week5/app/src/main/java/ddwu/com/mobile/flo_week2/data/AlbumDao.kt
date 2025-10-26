package ddwu.com.mobile.flo_week2.data

import ddwu.com.mobile.flo_week2.R

class AlbumDao {
    val albums = ArrayList<AlbumDto>()

    init {
        albums.add(AlbumDto("In My Bed", "bean", R.drawable.img_album_exp, 0, 60, false))
        albums.add(AlbumDto("LILAC", "아이유", R.drawable.img_album_exp2, 0, 70, false))
    }

    fun getAllAlbums() : ArrayList<AlbumDto> {
        return albums
    }
}