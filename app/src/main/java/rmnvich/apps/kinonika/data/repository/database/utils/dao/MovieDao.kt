package rmnvich.apps.kinonika.data.repository.database.utils.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import rmnvich.apps.kinonika.data.entity.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAllMovie() : Flowable<List<Movie>>
}