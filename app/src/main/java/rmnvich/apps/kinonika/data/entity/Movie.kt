package rmnvich.apps.kinonika.data.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Movie {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L

    var ratingIMDb: String = ""
    var hashTags: String = ""
    var country: String = ""
    var comment: String = ""
    var poster: String = ""
    var genre: String = ""
    var name: String = ""
    var plot: String = ""
    var year: String = ""
    var movieType: Int = 0
    var rating: Int = 0
}