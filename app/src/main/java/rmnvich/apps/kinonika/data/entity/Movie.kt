package rmnvich.apps.kinonika.data.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import android.graphics.Bitmap
import rmnvich.apps.kinonika.domain.converter.MovieConverter

@Entity
@TypeConverters(MovieConverter::class)
data class Movie(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var name: String,
    var poster: Bitmap,
    var rating: Int,
    var year: Int,
    var country: String,
    var genre: String,
    var hashTags: MutableList<Tag>,
    var plot: String,
    var comment: String
)