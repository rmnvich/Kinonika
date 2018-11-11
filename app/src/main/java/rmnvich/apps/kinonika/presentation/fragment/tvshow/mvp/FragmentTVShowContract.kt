package rmnvich.apps.kinonika.presentation.fragment.tvshow.mvp

import io.reactivex.Flowable
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.presentation.mvp.MvpModel
import rmnvich.apps.kinonika.presentation.mvp.MvpPresenter
import rmnvich.apps.kinonika.presentation.mvp.MvpView

interface FragmentTVShowContract {

    interface View : MvpView {

        fun setMovieToAdapter(movies: List<Movie>)
    }

    interface Presenter : MvpPresenter<View> {

    }

    interface Model : MvpModel {

        fun getAllTVShow(): Flowable<List<Movie>>
    }
}