package rmnvich.apps.kinonika.presentation.activity.make.mvp

import android.app.Activity
import android.content.Intent
import io.reactivex.disposables.CompositeDisposable
import rmnvich.apps.kinonika.R
import rmnvich.apps.kinonika.data.common.Constants.REQUEST_CODE_POSTER
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.presentation.mvp.PresenterBase
import java.io.IOException


class MakeReviewActivityPresenter(val compositeDisposable: CompositeDisposable,
                                  val model: MakeReviewActivityModel) :
        PresenterBase<MakeReviewActivityContract.View>(), MakeReviewActivityContract.Presenter {

    private var movieId: Long = -1L

    override fun detachView() {
        super.detachView()
        compositeDisposable.dispose()
    }

    override fun setMovieId(movieId: Long) {
        this.movieId = movieId
    }

    override fun viewIsReady() {
        if (movieId != -1L) {
            view?.showProgress()
            val disposable = model.getMovieById(movieId)
                    .subscribe({
                        view?.hideProgress()
                        view?.setMovie(it!!)
                    }, {
                        view?.hideProgress()
                        view?.showMessage(getString(R.string.error))
                    })
            compositeDisposable.add(disposable)
        } else view?.setMovie(Movie())
    }

    override fun onClickPoster() {
        val photoPickerIntent = Intent(Intent.ACTION_PICK).setType("image/*")
        (view as Activity).startActivityForResult(Intent.createChooser(photoPickerIntent,
                getString(R.string.select_a_file)), REQUEST_CODE_POSTER)
    }

    override fun onActivityResult(data: Intent?) {
        try {
            val bitmap = model.getBitmapFromGallery(data)
            view?.setBitmap(bitmap)
        } catch (e: IOException) {
            view?.setBitmap(null)
            view?.showMessage(getString(R.string.error))
        }
    }

    override fun onClickApply(movie: Movie) {
        view?.showProgress()
        val disposable = model.addMovie(movie)
                .subscribe({
                    view?.hideProgress()
                    (view as Activity).finish()
                }, {
                    view?.hideProgress()
                    view?.showMessage(getString(R.string.error))
                })
        compositeDisposable.add(disposable)
    }
}