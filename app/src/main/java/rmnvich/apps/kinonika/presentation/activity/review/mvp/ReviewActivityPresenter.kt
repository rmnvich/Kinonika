package rmnvich.apps.kinonika.presentation.activity.review.mvp

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.support.v4.content.FileProvider
import io.reactivex.disposables.CompositeDisposable
import rmnvich.apps.kinonika.R
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.presentation.mvp.base.PresenterBase
import java.io.File


class ReviewActivityPresenter(
        private val compositeDisposable: CompositeDisposable,
        private val model: ReviewActivityModel) :
        PresenterBase<ReviewActivityContract.View>(), ReviewActivityContract.Presenter {

    private var movieId: Long = -1L
    private var files: MutableList<File> = mutableListOf()

    override fun setMovieId(movieId: Long) {
        this.movieId = movieId
    }

    override fun viewIsReady() {
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
    }

    override fun onShareClicked(movie: Movie, bitmap: Bitmap) {
        view?.showProgress()
        val disposable = model.getPhotoUri(bitmap)
                .subscribe {
                    view?.hideProgress()
                    val shareBody = "\"${movie.name}\"\n\n" +
                            "${getString(R.string.country_ru)} ${movie.country}\n" +
                            "${getString(R.string.year_ru)} ${movie.year}\n" +
                            "${getString(R.string.genre_ru)} ${movie.genre}\n" +
                            "${getString(R.string.rating_ru)} ${movie.rating}/5\n" +
                            "${getString(R.string.imdb_ru)} ${movie.ratingIMDb}\n\n" +
                            "${getString(R.string.plot_ru)} \n\"${movie.plot}\"\n\n" +
                            "${getString(R.string.divider)}\n" +
                            getString(R.string.sent_through_kinonika)

                    val photoURI = FileProvider.getUriForFile((view as Activity).applicationContext,
                            "rmnvich.apps.kinonika.fileprovider", it)
                    files.add(it)

                    val intent = Intent(Intent.ACTION_SEND)
                    intent.type = "image/jpeg"
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    intent.putExtra(Intent.EXTRA_TEXT, shareBody)
                    intent.putExtra(Intent.EXTRA_STREAM, photoURI)
                    (view as Activity).startActivityForResult(Intent
                            .createChooser(intent, getString(R.string.send_via)), 0)
                }
        compositeDisposable.add(disposable)
    }

    override fun onActivityResult() {
        for (file in files) {
            file.delete()
        }
    }

    override fun detachView() {
        super.detachView()
        compositeDisposable.dispose()
    }
}