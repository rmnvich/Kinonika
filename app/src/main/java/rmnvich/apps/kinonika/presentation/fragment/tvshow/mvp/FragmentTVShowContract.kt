package rmnvich.apps.kinonika.presentation.fragment.tvshow.mvp

import io.reactivex.Flowable
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.presentation.mvp.MvpModel
import rmnvich.apps.kinonika.presentation.mvp.MvpPresenter
import rmnvich.apps.kinonika.presentation.mvp.MvpView

interface FragmentTVShowContract {

    interface View : MvpView {

        fun updateAdapter(movies: List<Movie>)

        fun setAnimationTypeToAdapter(position: Int, animationType: Int)

        fun showMessage(text: String)
    }

    interface Presenter : MvpPresenter<View> {

        fun onFabClicked()

        fun onClickMovie(movieId: Long)

        fun onLongClickMovie(movieId: Long, position: Int)

        fun setMovieType(movieType: Int)
    }

    interface Model : MvpModel {

        fun getAllTVShow(movieType: Int): Flowable<List<Movie>>
    }
}