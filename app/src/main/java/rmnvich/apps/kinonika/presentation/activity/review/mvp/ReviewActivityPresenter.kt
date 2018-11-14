package rmnvich.apps.kinonika.presentation.activity.review.mvp

import io.reactivex.disposables.CompositeDisposable
import rmnvich.apps.kinonika.R
import rmnvich.apps.kinonika.presentation.mvp.base.PresenterBase

class ReviewActivityPresenter(
        private val compositeDisposable: CompositeDisposable,
        private val model: ReviewActivityModel) :
        PresenterBase<ReviewActivityContract.View>(), ReviewActivityContract.Presenter {

    private var movieId: Long = -1L

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

    override fun detachView() {
        super.detachView()
        compositeDisposable.dispose()
    }
}