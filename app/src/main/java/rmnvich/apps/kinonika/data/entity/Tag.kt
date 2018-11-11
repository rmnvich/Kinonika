package rmnvich.apps.kinonika.data.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Tag(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var hashTag: String
)