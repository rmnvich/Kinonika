package rmnvich.apps.kinonika.presentation.fragment.series.mvp

import android.content.Intent
import android.support.v4.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import rmnvich.apps.kinonika.R
import rmnvich.apps.kinonika.data.common.Constants
import rmnvich.apps.kinonika.presentation.activity.make.mvp.MakeReviewActivity
import rmnvich.apps.kinonika.presentation.mvp.PresenterBase

class FragmentSeriesPresenter(
        private val compositeDisposable: CompositeDisposable,
        private val model: FragmentSeriesModel) :
        PresenterBase<FragmentSeriesContract.View>(), FragmentSeriesContract.Presenter {

    private var movieType: Int = -1

    override fun setMovieType(movieType: Int) {
        this.movieType = movieType
    }

    override fun viewIsReady() {
        view?.showProgress()
        val disposable = model.getAllSeries(movieType)
                .subscribe({
                    view?.hideProgress()
                    view?.updateAdapter(it)
                }, {
                    view?.hideProgress()
                    view?.showMessage(getString(R.string.error))
                }, { view?.hideProgress() })
        compositeDisposable.add(disposable)
    }

    override fun onClickMovie(movieId: Long) {

    }

    override fun onLongClickMovie(movieId: Long, position: Int) {
        view?.setAnimationTypeToAdapter(position, Constants.ACTION_TYPE_UPDATE)
        (view as Fragment).startActivity(Intent((view as Fragment).context,
                MakeReviewActivity::class.java)
                .putExtra(Constants.EXTRA_MOVIE_TYPE, Constants.REQUEST_CODE_SERIES)
                .putExtra(Constants.EXTRA_MOVIE_ID, movieId))
    }

    override fun onFabClicked() {
        view?.setAnimationTypeToAdapter(0, Constants.ACTION_TYPE_INSERT)
        (view as Fragment).startActivity(Intent((view as Fragment).context,
                MakeReviewActivity::class.java)
                .putExtra(Constants.EXTRA_MOVIE_TYPE, Constants.REQUEST_CODE_SERIES))
    }

    override fun detachView() {
        super.detachView()
        compositeDisposable.dispose()
    }
}