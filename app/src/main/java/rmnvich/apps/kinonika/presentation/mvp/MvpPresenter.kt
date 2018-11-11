package rmnvich.apps.kinonika.presentation.mvp

interface MvpPresenter<V : MvpView> {

    fun attachView(mvpView: V)

    fun viewIsReady()

    fun detachView()
}