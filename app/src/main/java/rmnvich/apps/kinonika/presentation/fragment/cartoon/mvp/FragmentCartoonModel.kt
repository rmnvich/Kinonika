package rmnvich.apps.kinonika.presentation.fragment.cartoon.mvp

import io.reactivex.Flowable
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.data.repository.database.DatabaseRepository
import rmnvich.apps.kinonika.presentation.mvp.MvpModel

class FragmentCartoonModel(val databaseRepository: DatabaseRepository)
    : FragmentCartoonContract.Model {

    override fun getAllCartoons(): Flowable<List<Movie>> {
        TODO("not implemented")
    }
}