package rmnvich.apps.kinonika.presentation.fragment.film.mvp

import io.reactivex.Flowable
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.presentation.mvp.MvpModel
import rmnvich.apps.kinonika.presentation.mvp.MvpPresenter
import rmnvich.apps.kinonika.presentation.mvp.MvpView

interface FragmentFilmContract {

    interface View : MvpView {

        fun updateAdapter(movies: List<Movie>)

        fun setAnimationTypeToAdapter(position: Int, animationType: Int)

        fun showFilterDialog(tags: List<String>)

        fun showMessage(text: String)
    }

    interface Presenter : MvpPresenter<View> {

        fun onFabClicked()

        fun onClickFilter()

        fun onFilterApply(genre: String, tag: String, rating: Int, year: String)

        fun onClickMovie(movieId: Long)

        fun onLongClickMovie(movieId: Long, position: Int)

        fun setMovieType(movieType: Int)
    }

    interface Model : MvpModel {

        fun getTags(): Flowable<List<String>>

        fun getAllFilms(movieType: Int): Flowable<List<Movie>>

        fun getAllFilteredFilms(movieType: Int, genre: String, tag: String,
                                rating: Int, year: String): Flowable<List<Movie>>
    }
}