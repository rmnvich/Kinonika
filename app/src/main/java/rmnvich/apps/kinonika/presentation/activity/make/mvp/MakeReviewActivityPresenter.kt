package rmnvich.apps.kinonika.presentation.activity.make.mvp

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.support.v4.app.FragmentActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.CompositeDisposable
import rmnvich.apps.kinonika.R
import rmnvich.apps.kinonika.data.common.Constants.REQUEST_CODE_POSTER
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.data.entity.Tag
import rmnvich.apps.kinonika.presentation.mvp.PresenterBase
import java.io.IOException

class MakeReviewActivityPresenter(
        private val compositeDisposable: CompositeDisposable,
        private val model: MakeReviewActivityModel) :
        PresenterBase<MakeReviewActivityContract.View>(), MakeReviewActivityContract.Presenter {

    private var mRxPermissions: RxPermissions? = null
    private var movieId: Long = -1L

    private var listOfTags: List<String> = arrayListOf()

    override fun attachView(mvpView: MakeReviewActivityContract.View) {
        super.attachView(mvpView)
        mRxPermissions = RxPermissions((view as FragmentActivity))
    }

    override fun detachView() {
        super.detachView()
        compositeDisposable.dispose()
    }

    override fun setMovieId(movieId: Long) {
        this.movieId = movieId
    }

    override fun viewIsReady() {
        if (movieId != -1L) {
            val movieDisposable = model.getMovieById(movieId)
                    .subscribe({
                        view?.hideProgress()
                        view?.setMovie(it!!)
                    }, {
                        view?.hideProgress()
                        view?.showMessage(getString(R.string.error))
                    })
            compositeDisposable.add(movieDisposable)
        } else view?.setMovie(Movie())
    }

    override fun getTags() {
        view?.showProgress()
        val tagsDisposable = model.getTags()
                .subscribe({
                    view?.hideProgress()
                    val list: MutableList<String> = arrayListOf()
                    for (tag in it) {
                        list.add(tag.hashTag)
                    }

                    listOfTags = list
                    view?.setTagsToAutoCompleteTextView(list)
                }, {
                    view?.hideProgress()
                    view?.showMessage(getString(R.string.error))
                })
        compositeDisposable.add(tagsDisposable)
    }

    override fun showImageDialog() {
        val photoPickerIntent = Intent(Intent.ACTION_PICK).setType("image/*")
        (view as Activity).startActivityForResult(
                Intent.createChooser(
                        photoPickerIntent, getString(R.string.select_a_file)
                ), REQUEST_CODE_POSTER
        )
    }

    override fun onClickPoster() {
        requestPermissions()
    }

    override fun onActivityResult(data: Intent?) {
        try {
            view?.showProgress()
            val disposable = model.getFilePath(data)
                    .subscribe {
                        view?.setBitmap(it)
                        view?.hideProgress()
                    }
            compositeDisposable.add(disposable)
        } catch (e: IOException) {
            view?.setBitmap("")
            view?.showMessage(getString(R.string.error))
        }
    }

    override fun onClickApply(movie: Movie) {
        if (isDataCorrect(movie)) {
            view?.showProgress()
            val disposable = model.addMovie(movie, listOfTags)
                    .subscribe({
                        view?.hideProgress()
                        (view as Activity).finish()
                    }, {
                        view?.hideProgress()
                        view?.showMessage(getString(R.string.error))
                    })
            compositeDisposable.add(disposable)
        } else view?.showMessage(getString(R.string.empty_field_error))
    }

    override fun isDataCorrect(movie: Movie): Boolean {
        return !(movie.name.isEmpty() ||
                movie.country.isEmpty() ||
                movie.year.isEmpty() ||
                movie.genre.isEmpty() ||
                movie.ratingIMDb.isEmpty() ||
                movie.plot.isEmpty())
    }

    override fun requestPermissions() {
        val disposable = mRxPermissions?.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                ?.subscribe { permission ->
                    if (permission) {
                        showImageDialog()
                    } else view?.showMessage(getString(R.string.error))
                }
        compositeDisposable.add(disposable!!)
    }
}