package rmnvich.apps.kinonika.presentation.fragment.film.mvp

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import rmnvich.apps.kinonika.R
import rmnvich.apps.kinonika.app.App
import rmnvich.apps.kinonika.data.common.Constants.EXTRA_MOVIE_TYPE
import rmnvich.apps.kinonika.data.common.Constants.REQUEST_CODE_FILM
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.databinding.FragmentFilmBinding
import rmnvich.apps.kinonika.presentation.activity.make.mvp.MakeReviewActivity
import javax.inject.Inject

class FragmentFilm : Fragment(), FragmentFilmContract.View {

    private lateinit var binding: FragmentFilmBinding

    @Inject
    lateinit var mPresenter: FragmentFilmPresenter

    companion object {
        fun newInstance(): FragmentFilm {
            return FragmentFilm()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_film, container, false)
        binding.handler = this

        binding.fabAddFilm.setOnClickListener {
            activity?.startActivity(Intent(activity, MakeReviewActivity::class.java)
                    .putExtra(EXTRA_MOVIE_TYPE, REQUEST_CODE_FILM))
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.attachView(this)
        mPresenter.viewIsReady()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        App.getApp(activity?.applicationContext).componentsHolder
                .getComponent(javaClass).inject(this)
    }

    override fun setMovieToAdapter(movies: List<Movie>) {

    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun onDetach() {
        super.onDetach()
        App.getApp(activity?.applicationContext)
                .componentsHolder.releaseComponent(javaClass)
    }
}