package rmnvich.apps.kinonika.presentation.mvp.movie

import io.reactivex.Flowable
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.data.repository.database.DatabaseRepository

class FragmentMovieModel(
        private val databaseRepository: DatabaseRepository) :
        FragmentMovieContract.Model {

    override fun getTags(): Flowable<List<String>> {
        return databaseRepository.getAllTags()
    }

    override fun getAllFilteredMovies(movieType: Int, genre: String, tag: String,
                                      rating: Int, year: String): Flowable<List<Movie>> {
        return databaseRepository.getAllFilteredMovies(movieType, genre, tag, year, rating)
    }
}