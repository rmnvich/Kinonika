package rmnvich.apps.kinonika.presentation.activity.make.mvp

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.MultiAutoCompleteTextView
import com.bumptech.glide.Glide
import rmnvich.apps.kinonika.R
import rmnvich.apps.kinonika.app.App
import rmnvich.apps.kinonika.data.common.Constants.*
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.databinding.ActivityMakeReviewBinding
import rmnvich.apps.kinonika.presentation.activity.make.dagger.MakeReviewActivityModule
import rmnvich.apps.kinonika.presentation.custom.SpaceTokenizer
import java.io.File
import javax.inject.Inject

class MakeReviewActivity : AppCompatActivity(), MakeReviewActivityContract.View {

    private lateinit var binding: ActivityMakeReviewBinding

    @Inject
    lateinit var mPresenter: MakeReviewActivityPresenter

    @Inject
    lateinit var mGenreAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
                R.layout.activity_make_review)
        binding.handler = this

        App.getApp(this).componentsHolder.getComponent(javaClass,
                MakeReviewActivityModule(this)).inject(this)
    }

    @Inject
    fun init() {
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        binding.etGenre.setAdapter(mGenreAdapter)
        binding.etGenre.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())

        mPresenter.attachView(this)
        if (intent.extras != null) {
            mPresenter.setMovieId(intent.getLongExtra(
                    EXTRA_MOVIE_ID, -1L))
        }
        mPresenter.viewIsReady()
        mPresenter.loadTags()
    }

    override fun setMovie(movie: Movie) {
        movie.movieType = intent.extras
                .getInt(EXTRA_MOVIE_TYPE, -1)
        setBitmap(movie.poster)

        binding.movie = movie
        binding.invalidateAll()
    }

    override fun setTagsToAutoCompleteTextView(tags: List<String>) {
        binding.etTag.setAdapter(ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, tags))
        binding.etTag.setTokenizer(SpaceTokenizer())
    }

    override fun setBitmap(filePath: String) {
        binding.movie?.poster = filePath

        Glide.with(this)
                .load(File(filePath))
                .into(binding.ivPoster)
        binding.invalidateAll()
    }

    override fun onClickPoster() {
        mPresenter.onClickPoster()
    }

    override fun onClickApply() {
        mPresenter.onClickApply(binding.movie!!)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_POSTER && resultCode ==
                Activity.RESULT_OK && data != null
        ) {
            mPresenter.onActivityResult(data)
        }
    }

    override fun showMessage(text: String) {
        Snackbar.make(binding.root, text, Snackbar.LENGTH_LONG).show()
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
