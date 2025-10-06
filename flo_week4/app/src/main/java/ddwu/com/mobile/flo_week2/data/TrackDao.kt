package ddwu.com.mobile.flo_week2.data

import ddwu.com.mobile.flo_week2.R

class TrackDao {
    val tracks = ArrayList<TrackDto>()

    init {
        tracks.add(TrackDto("01", "LILAC", "아이유"))
        tracks.add(TrackDto("02", "Lady", "Kenshi Yonezu"))
        tracks.add(TrackDto("03", "Butter", "BTS"))
        tracks.add(TrackDto("04", "사랑은 늘 도망가", "아이유"))
        tracks.add(TrackDto("05", "step one", "신재범"))
        tracks.add(TrackDto("06", "얼어 붙은 도시", "데카브리"))
    }
}