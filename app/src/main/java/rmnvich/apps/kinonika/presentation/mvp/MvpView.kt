package rmnvich.apps.kinonika.presentation.mvp

interface MvpView {

    fun showMessage(text: String)

    fun showProgress()

    fun hideProgress()
}
