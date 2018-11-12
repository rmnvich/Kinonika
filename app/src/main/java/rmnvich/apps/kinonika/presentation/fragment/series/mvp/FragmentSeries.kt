package rmnvich.apps.kinonika.presentation.fragment.series.mvp

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import rmnvich.apps.kinonika.R
import rmnvich.apps.kinonika.app.App
import rmnvich.apps.kinonika.data.common.Constants.*
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.databinding.FragmentSeriesBinding
import rmnvich.apps.kinonika.presentation.activity.make.mvp.MakeReviewActivity
import rmnvich.apps.kinonika.presentation.activity.review.ViewReviewActivity
import rmnvich.apps.kinonika.presentation.adapter.MovieAdapter
import javax.inject.Inject

class FragmentSeries : Fragment(), FragmentSeriesContract.View {

    private lateinit var binding: FragmentSeriesBinding

    @Inject
    lateinit var mPresenter: FragmentSeriesPresenter

    @Inject
    lateinit var mAdapter: MovieAdapter

    companion object {
        fun newInstance(): FragmentSeries {
            return FragmentSeries()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_series, container, false)
        binding.handler = this

        binding.recyclerSeries.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false)
        binding.recyclerSeries.adapter = mAdapter

        mAdapter.setOnClickMovieListener(object : MovieAdapter.OnClickMovieListener {
            override fun onClickMovie(movieId: Long) {
                mPresenter.onClickMovie(movieId)
            }

            override fun onLongClickMovie(movieId: Long, position: Int) {
                mPresenter.onLongClickMovie(movieId, position)
            }
        })

        binding.fabAddSeries.setOnClickListener {
            mPresenter.onFabClicked()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.attachView(this)
        mPresenter.setMovieType(REQUEST_CODE_SERIES)
        mPresenter.viewIsReady()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        App.getApp(activity?.applicationContext).componentsHolder
                .getComponent(javaClass).inject(this)
    }

    override fun updateAdapter(movies: List<Movie>) {
        mAdapter.setData(movies)
    }

    override fun setAnimationTypeToAdapter(position: Int, animationType: Int) {
        mAdapter.setActionType(animationType)
        mAdapter.setPosition(position)
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

    override fun onDetach() {
        super.onDetach()
        App.getApp(activity?.applicationContext)
                .componentsHolder.releaseComponent(javaClass)
    }
}