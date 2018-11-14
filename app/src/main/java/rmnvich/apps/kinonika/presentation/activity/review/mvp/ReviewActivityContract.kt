package rmnvich.apps.kinonika.presentation.activity.review.mvp

import android.graphics.Bitmap
import io.reactivex.Observable
import io.reactivex.Single
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.presentation.mvp.base.MvpModel
import rmnvich.apps.kinonika.presentation.mvp.base.MvpPresenter
import rmnvich.apps.kinonika.presentation.mvp.base.MvpView
import java.io.File

interface ReviewActivityContract {

    interface View : MvpView {

        fun showMessage(text: String)

        fun setMovie(movie: Movie)

        fun setBitmap(filePath: String)
    }

    interface Presenter : MvpPresenter<View> {

        fun setMovieId(movieId: Long)

        fun onShareClicked(movie: Movie, bitmap: Bitmap)

        fun onActivityResult()
    }

    interface Model : MvpModel {

        fun getPhotoUri(bitmap: Bitmap): Observable<File>

        fun getMovieById(movieId: Long): Single<Movie>
    }
}