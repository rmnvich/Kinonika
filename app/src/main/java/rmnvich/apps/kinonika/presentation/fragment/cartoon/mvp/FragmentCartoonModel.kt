package rmnvich.apps.kinonika.presentation.fragment.cartoon.mvp

import io.reactivex.Flowable
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.data.repository.database.DatabaseRepository

class FragmentCartoonModel(
        private val databaseRepository: DatabaseRepository) :
        FragmentCartoonContract.Model {

    override fun getAllCartoons(movieType: Int): Flowable<List<Movie>> {
        return databaseRepository.getAllMovies(movieType)
    }
}