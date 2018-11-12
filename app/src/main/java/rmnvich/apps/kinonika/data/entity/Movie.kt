package rmnvich.apps.kinonika.data.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import android.graphics.Bitmap
import rmnvich.apps.kinonika.domain.converter.MovieConverter

@Entity
@TypeConverters(MovieConverter::class)
class Movie {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L

    var ratingIMDb: String = ""
    var hashTags: String = ""
    var country: String = ""
    var comment: String = ""
    var genre: String = ""
    var name: String = ""
    var plot: String = ""
    var movieType: Int = 0
    var rating: Int = 0
    var year: Int = 0
    var poster: Bitmap? = null
}