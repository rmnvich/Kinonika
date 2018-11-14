package rmnvich.apps.kinonika.presentation.activity.review.mvp

import io.reactivex.Single
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.presentation.mvp.base.MvpModel
import rmnvich.apps.kinonika.presentation.mvp.base.MvpPresenter
import rmnvich.apps.kinonika.presentation.mvp.base.MvpView

interface ReviewActivityContract {

    interface View : MvpView {

        fun showMessage(text: String)

        fun setMovie(movie: Movie)

        fun setBitmap(filePath: String)
    }

    interface Presenter : MvpPresenter<View> {

        fun setMovieId(movieId: Long)
    }

    interface Model : MvpModel {

        fun getMovieById(movieId: Long): Single<Movie>
    }
}