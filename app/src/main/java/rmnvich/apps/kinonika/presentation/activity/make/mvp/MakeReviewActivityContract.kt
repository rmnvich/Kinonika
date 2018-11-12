package rmnvich.apps.kinonika.presentation.activity.make.mvp

import android.content.Intent
import android.graphics.Bitmap
import io.reactivex.Completable
import io.reactivex.Single
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.presentation.mvp.MvpModel
import rmnvich.apps.kinonika.presentation.mvp.MvpPresenter
import rmnvich.apps.kinonika.presentation.mvp.MvpView

interface MakeReviewActivityContract {

    interface View : MvpView {

        fun showMessage(text: String)

        fun setMovie(movie: Movie)

        fun setBitmap(bitmap: Bitmap?)

        fun onClickPoster()

        fun onClickApply()
    }

    interface Presenter : MvpPresenter<View> {

        fun setMovieId(movieId: Long)

        fun onClickPoster()

        fun onActivityResult(data: Intent?)

        fun onClickApply(movie: Movie)
    }

    interface Model : MvpModel {

        fun getBitmapFromGallery(data: Intent?) : Bitmap

        fun getMovieById(movieId: Long): Single<Movie>

        fun addMovie(movie: Movie): Completable
    }
}