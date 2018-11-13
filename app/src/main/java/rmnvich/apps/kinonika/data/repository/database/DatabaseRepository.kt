package rmnvich.apps.kinonika.data.repository.database

import android.util.Log
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.data.entity.Tag
import rmnvich.apps.kinonika.data.repository.database.utils.AppDatabase
import java.util.concurrent.TimeUnit

class DatabaseRepository(appDatabase: AppDatabase) {

    private val movieDao = appDatabase.movieDao()
    private val tagDao = appDatabase.tagDao()

    fun addMovieAndTags(movie: Movie, tags: List<Tag>): Completable {
        return addTags(tags)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .andThen(addMovie(movie))
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun addMovie(movie: Movie): Completable {
        return Completable.fromAction {
            movieDao.insertMovie(movie)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun addTags(tags: List<Tag>): Completable {
        return Completable.fromAction {
            tagDao.addTags(tags)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getAllTags(): Flowable<List<String>> {
        return tagDao.getAllTags()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .flatMap { it ->
                    Flowable.fromIterable(it)
                            .map { it.hashTag }
                            .toList()
                            .toFlowable()
                }.observeOn(AndroidSchedulers.mainThread())
    }

    fun getMovieById(id: Long): Single<Movie> {
        return movieDao.getMovieById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getAllMovies(movieType: Int): Flowable<List<Movie>> {
        return movieDao.getAllMovies(movieType)
                .subscribeOn(Schedulers.io())
                .delay(450, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getAllFilteredMovies(movieType: Int, genre: String, tag: String,
                             year: String, rating: Int): Flowable<List<Movie>> {
        return if (rating == 0) {
            movieDao.getAllFilteredMovies(movieType, "%$genre%", "%$tag%",
                    "%$year%")
                    .subscribeOn(Schedulers.io())
                    .delay(450, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
        } else movieDao.getAllFilteredMovies(movieType, "%$genre%", "%$tag%",
                "%$year%", rating)
                .subscribeOn(Schedulers.io())
                .delay(450, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
    }
}