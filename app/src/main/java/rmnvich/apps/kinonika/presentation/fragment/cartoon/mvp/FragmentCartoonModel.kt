package rmnvich.apps.kinonika.presentation.fragment.cartoon.mvp

import io.reactivex.Flowable
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.data.repository.database.DatabaseRepository
import rmnvich.apps.kinonika.presentation.fragment.film.mvp.FragmentMovieContract

class FragmentCartoonModel(
        private val databaseRepository: DatabaseRepository) :
        FragmentMovieContract.Model {

    override fun getTags(): Flowable<List<String>> {
        return databaseRepository.getAllTags()
    }

    override fun getAllMovies(movieType: Int): Flowable<List<Movie>> {
        return databaseRepository.getAllMovies(movieType)
    }

    override fun getAllFilteredMovies(movieType: Int, genre: String, tag: String,
                                      rating: Int, year: String): Flowable<List<Movie>> {
        return databaseRepository.getAllFilteredMovies(movieType, genre, tag, year, rating)
    }}