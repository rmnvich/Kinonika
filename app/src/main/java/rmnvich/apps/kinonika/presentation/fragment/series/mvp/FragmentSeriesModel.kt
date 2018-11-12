package rmnvich.apps.kinonika.presentation.fragment.series.mvp

import io.reactivex.Flowable
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.data.repository.database.DatabaseRepository

class FragmentSeriesModel(
        private val databaseRepository: DatabaseRepository) :
        FragmentSeriesContract.Model {

    override fun getAllSeries(movieType: Int): Flowable<List<Movie>> {
        return databaseRepository.getAllMovies(movieType)
    }
}