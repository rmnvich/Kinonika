package rmnvich.apps.kinonika.presentation.fragment.film.mvp

import io.reactivex.Flowable
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.data.repository.database.DatabaseRepository

class FragmentFilmModel(
        private val databaseRepository: DatabaseRepository) :
        FragmentFilmContract.Model {

    override fun getAllFilms(movieType: Int): Flowable<List<Movie>> {
        return databaseRepository.getAllMovies(movieType)
    }
}