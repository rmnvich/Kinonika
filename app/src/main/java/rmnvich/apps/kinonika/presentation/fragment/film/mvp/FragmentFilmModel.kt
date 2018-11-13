package rmnvich.apps.kinonika.presentation.fragment.film.mvp

import io.reactivex.Flowable
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.data.repository.database.DatabaseRepository

class FragmentFilmModel(
        private val databaseRepository: DatabaseRepository) :
        FragmentFilmContract.Model {

    override fun getTags(): Flowable<List<String>> {
        return databaseRepository.getAllTags()
    }

    override fun getAllFilms(movieType: Int): Flowable<List<Movie>> {
        return databaseRepository.getAllMovies(movieType)
    }

    override fun getAllFilteredFilms(movieType: Int, genre: String, tag: String,
                                     rating: Int, year: String): Flowable<List<Movie>> {
        return databaseRepository.getAllFilteredMovies(movieType, genre, tag, year, rating)
    }
}