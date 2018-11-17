package rmnvich.apps.kinonika.presentation.mvp.movie

import android.content.Intent
import android.support.v4.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import rmnvich.apps.kinonika.R
import rmnvich.apps.kinonika.data.common.Constants
import rmnvich.apps.kinonika.presentation.activity.make.mvp.MakeReviewActivity
import rmnvich.apps.kinonika.presentation.activity.review.mvp.ViewReviewActivity
import rmnvich.apps.kinonika.presentation.mvp.base.PresenterBase

class FragmentMoviePresenter(
        private val compositeDisposable: CompositeDisposable,
        private val model: FragmentMovieModel) :
        PresenterBase<FragmentMovieContract.View>(), FragmentMovieContract.Presenter {

    private var movieType: Int = -1

    private var allMoviesDisposable: Disposable? = null
    private var filteredMoviesDisposable: Disposable? = null

    override fun setMovieType(movieType: Int) {
        this.movieType = movieType
    }

    override fun viewIsReady() {
        view?.showProgress()
        allMoviesDisposable = model.getAllMovies(movieType)
                .subscribe({
                    view?.hideProgress()
                    view?.updateAdapter(it)
                }, {
                    view?.hideProgress()
                    view?.showMessage(getString(R.string.error))
                }, { view?.hideProgress() })
    }

    override fun onClickFilter() {
        view?.showProgress()
        val disposable = model.getTags()
                .subscribe({
                    view?.hideProgress()
                    view?.showFilterDialog(it)
                }, {
                    view?.hideProgress()
                    view?.showMessage(getString(R.string.error))
                })
        compositeDisposable.add(disposable)
    }

    override fun onFilterApply(genre: String, tag: String, rating: Int, year: String) {
        allMoviesDisposable?.dispose()

        view?.showProgress()
        filteredMoviesDisposable = model.getAllFilteredMovies(movieType, genre, tag, rating, year)
                .subscribe({
                    view?.hideProgress()
                    view?.updateAdapter(it)
                }, {
                    view?.hideProgress()
                    view?.showMessage(getString(R.string.error))
                }, { view?.hideProgress() })
    }

    override fun onClickMovie(movieId: Long) {
        (view as Fragment).startActivity(Intent((view as Fragment).context,
                ViewReviewActivity::class.java)
                .putExtra(Constants.EXTRA_MOVIE_ID, movieId))
    }

    override fun onLongClickMovie(movieId: Long, position: Int) {
        (view as Fragment).startActivity(Intent((view as Fragment).context,
                MakeReviewActivity::class.java)
                .putExtra(Constants.EXTRA_MOVIE_TYPE, movieType)
                .putExtra(Constants.EXTRA_MOVIE_ID, movieId))
    }

    override fun onFabClicked() {
        (view as Fragment).startActivity(Intent((view as Fragment).context,
                MakeReviewActivity::class.java)
                .putExtra(Constants.EXTRA_MOVIE_TYPE, movieType))
    }

    override fun detachView() {
        super.detachView()
        compositeDisposable.dispose()
    }
}