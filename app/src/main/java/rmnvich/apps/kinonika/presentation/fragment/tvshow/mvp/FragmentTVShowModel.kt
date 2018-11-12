package rmnvich.apps.kinonika.presentation.fragment.tvshow.mvp

import io.reactivex.Flowable
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.data.repository.database.DatabaseRepository

class FragmentTVShowModel(
        private val databaseRepository: DatabaseRepository) :
        FragmentTVShowContract.Model {

    override fun getAllTVShow(movieType: Int): Flowable<List<Movie>> {
        return databaseRepository.getAllMovies(movieType)
    }}