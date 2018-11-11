package rmnvich.apps.kinonika.presentation.fragment.film.mvp

import io.reactivex.disposables.CompositeDisposable
import rmnvich.apps.kinonika.presentation.mvp.PresenterBase

class FragmentFilmPresenter(
    private val compositeDisposable: CompositeDisposable,
    val model: FragmentFilmModel
) : PresenterBase<FragmentFilmContract.View>(), FragmentFilmContract.Presenter {

    override fun viewIsReady() {

    }

    override fun detachView() {
        super.detachView()
        compositeDisposable.dispose()
    }

}