package rmnvich.apps.kinonika.data.repository.database

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.data.repository.database.utils.AppDatabase

class DatabaseRepository(appDatabase: AppDatabase) {

    private val movieDao = appDatabase.movieDao()
    private val tagDao = appDatabase.tagDao()

    fun addMovie(movie: Movie): Completable {
        return Completable.fromAction {
            movieDao.insertMovie(movie)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getMovieById(id: Long): Single<Movie> {
        return movieDao.getMovieById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getAllMovies(movieType: Int): Flowable<List<Movie>> {
        return movieDao.getAllMovies(movieType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}