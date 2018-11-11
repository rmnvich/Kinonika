package rmnvich.apps.kinonika.presentation.fragment.tvshow.mvp

import io.reactivex.Flowable
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.data.repository.database.DatabaseRepository

class FragmentTVShowModel(val databaseRepository: DatabaseRepository) : FragmentTVShowContract.Model {

    override fun getAllTVShow(): Flowable<List<Movie>> {
        TODO("not implemented")
    }
}