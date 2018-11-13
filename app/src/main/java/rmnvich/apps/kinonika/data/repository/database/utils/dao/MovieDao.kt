package rmnvich.apps.kinonika.data.repository.database.utils.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import io.reactivex.Single
import rmnvich.apps.kinonika.data.entity.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie WHERE movieType = :movieType ORDER BY id DESC")
    fun getAllMovies(movieType: Int): Flowable<List<Movie>>

    @Query("SELECT * FROM movie WHERE hashTags LIKE :tag AND genre LIKE :genre AND year LIKE :year AND rating LIKE :rating AND movieType = :movieType ORDER BY id DESC")
    fun getAllFilteredMovies(movieType: Int, genre: String, tag: String,
                             year: String, rating: Int): Flowable<List<Movie>>

    @Query("SELECT * FROM movie WHERE hashTags LIKE :tag AND genre LIKE :genre AND year LIKE :year AND movieType = :movieType ORDER BY id DESC")
    fun getAllFilteredMovies(movieType: Int, genre: String, tag: String, year: String): Flowable<List<Movie>>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getMovieById(id: Long): Single<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)
}