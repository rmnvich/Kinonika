package rmnvich.apps.kinonika.presentation.fragment.cartoon.mvp

import io.reactivex.disposables.CompositeDisposable
import rmnvich.apps.kinonika.presentation.mvp.PresenterBase

class FragmentCartoonPresenter(private val compositeDisposable: CompositeDisposable,
                               val model: FragmentCartoonModel)
    : PresenterBase<FragmentCartoonContract.View>(), FragmentCartoonContract.Presenter {

    override fun viewIsReady() {

    }

    override fun detachView() {
        super.detachView()
        compositeDisposable.dispose()
    }

}