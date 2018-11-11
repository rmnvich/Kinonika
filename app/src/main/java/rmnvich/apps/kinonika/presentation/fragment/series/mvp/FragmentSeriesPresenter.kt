package rmnvich.apps.kinonika.presentation.fragment.series.mvp

import io.reactivex.disposables.CompositeDisposable
import rmnvich.apps.kinonika.presentation.mvp.PresenterBase

class FragmentSeriesPresenter(
    private val compositeDisposable: CompositeDisposable,
    val model: FragmentSeriesModel
) : PresenterBase<FragmentSeriesContract.View>(), FragmentSeriesContract.Presenter {

    override fun viewIsReady() {

    }

    override fun detachView() {
        super.detachView()
        compositeDisposable.dispose()
    }

}