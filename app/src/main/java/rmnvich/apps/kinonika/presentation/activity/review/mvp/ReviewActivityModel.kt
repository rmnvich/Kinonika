package rmnvich.apps.kinonika.presentation.activity.review.mvp

import io.reactivex.Single
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.data.repository.database.DatabaseRepository

class ReviewActivityModel(
        private val databaseRepository: DatabaseRepository) :
        ReviewActivityContract.Model {

    override fun getMovieById(movieId: Long): Single<Movie> {
        return databaseRepository.getMovieById(movieId)
    }
}