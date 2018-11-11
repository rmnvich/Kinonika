package rmnvich.apps.kinonika.presentation.fragment.tvshow.mvp

import io.reactivex.disposables.CompositeDisposable
import rmnvich.apps.kinonika.presentation.mvp.PresenterBase

class FragmentTVShowPresenter(
    private val compositeDisposable: CompositeDisposable,
    val model: FragmentTVShowModel
) : PresenterBase<FragmentTVShowContract.View>(), FragmentTVShowContract.Presenter {

    override fun viewIsReady() {

    }

    override fun detachView() {
        super.detachView()
        compositeDisposable.dispose()
    }

}