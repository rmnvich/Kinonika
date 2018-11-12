package rmnvich.apps.kinonika.presentation.activity.review.mvp

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import rmnvich.apps.kinonika.R
import rmnvich.apps.kinonika.app.App
import rmnvich.apps.kinonika.data.common.Constants
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.databinding.ActivityViewReviewBinding
import rmnvich.apps.kinonika.presentation.activity.make.dagger.MakeReviewActivityModule
import java.io.File
import javax.inject.Inject

class ViewReviewActivity : AppCompatActivity(), ReviewActivityContract.View {

    private lateinit var binding: ActivityViewReviewBinding

    @Inject
    lateinit var mPresenter: ReviewActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
                R.layout.activity_view_review)
        binding.handler = this

        App.getApp(this).componentsHolder
                .getComponent(javaClass).inject(this)
    }

    @Inject
    fun init() {
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        mPresenter.attachView(this)
        mPresenter.setMovieId(intent.getLongExtra(
                Constants.EXTRA_MOVIE_ID, -1L))
        mPresenter.viewIsReady()
    }

    override fun showMessage(text: String) {
        Snackbar.make(binding.root, text, Snackbar.LENGTH_LONG).show()
    }

    override fun setMovie(movie: Movie) {
        setBitmap(movie.poster)
        binding.movie = movie
    }

    override fun setBitmap(filePath: String) {
        Glide.with(this)
                .load(File(filePath))
                .into(binding.ivPoster)
    }

    override fun showProgress() {
        binding.progressBar.smoothToShow()
    }

    override fun hideProgress() {
        binding.progressBar.smoothToHide()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) {
            App.getApp(this).componentsHolder
                    .releaseComponent(javaClass)
            mPresenter.detachView()
        }
    }
}
