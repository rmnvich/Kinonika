package rmnvich.apps.kinonika.data.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Tag(var hashTag: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L
}