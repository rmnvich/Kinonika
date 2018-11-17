package rmnvich.apps.kinonika.presentation.mvp.movie

import io.reactivex.Flowable
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.presentation.mvp.base.MvpModel
import rmnvich.apps.kinonika.presentation.mvp.base.MvpPresenter
import rmnvich.apps.kinonika.presentation.mvp.base.MvpView

interface FragmentMovieContract {

    interface View : MvpView {

        fun updateAdapter(movies: List<Movie>)

        fun onFabClicked()

        fun showFilterDialog(tags: List<String>)

        fun showMessage(text: String)
    }

    interface Presenter : MvpPresenter<View> {

        fun onFabClicked()

        fun onClickFilter()

        fun loadMovies(genre: String, tag: String, rating: Int, year: String)

        fun onFilterApply(genre: String, tag: String, rating: Int, year: String)

        fun onClickMovie(movieId: Long)

        fun onLongClickMovie(movieId: Long, position: Int)

        fun setMovieType(movieType: Int)
    }

    interface Model : MvpModel {

        fun getTags(): Flowable<List<String>>

        fun getAllFilteredMovies(movieType: Int, genre: String, tag: String,
                                 rating: Int, year: String): Flowable<List<Movie>>
    }
}