package rmnvich.apps.kinonika.presentation.fragment.cartoon.mvp

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
import butterknife.OnClick
import rmnvich.apps.kinonika.R
import rmnvich.apps.kinonika.app.App
import rmnvich.apps.kinonika.data.common.Constants
import rmnvich.apps.kinonika.data.common.Constants.REQUEST_CODE_CARTOON
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.databinding.FragmentCartoonBinding
import rmnvich.apps.kinonika.presentation.activity.make.mvp.MakeReviewActivity
import rmnvich.apps.kinonika.presentation.adapter.MovieAdapter
import javax.inject.Inject

class FragmentCartoon : Fragment(), FragmentCartoonContract.View {

    private lateinit var binding: FragmentCartoonBinding

    @Inject
    lateinit var mPresenter: FragmentCartoonPresenter

    @Inject
    lateinit var mAdapter: MovieAdapter

    companion object {
        fun newInstance(): FragmentCartoon {
            return FragmentCartoon()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cartoon, container, false)
        binding.handler = this

        binding.recyclerCartoons.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false)
        binding.recyclerCartoons.adapter = mAdapter

        mAdapter.setOnClickMovieListener(object : MovieAdapter.OnClickMovieListener {
            override fun onClickMovie(movieId: Long) {
                mPresenter.onClickMovie(movieId)
            }

            override fun onLongClickMovie(movieId: Long, position: Int) {
                mPresenter.onLongClickMovie(movieId, position)
            }
        })

        binding.fabAddCartoon.setOnClickListener {
            mPresenter.onFabClicked()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.attachView(this)
        mPresenter.setMovieType(REQUEST_CODE_CARTOON)
        mPresenter.viewIsReady()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        App.getApp(activity?.applicationContext).componentsHolder
                .getComponent(javaClass).inject(this)
    }

    override fun uodateAdapter(movies: List<Movie>) {
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