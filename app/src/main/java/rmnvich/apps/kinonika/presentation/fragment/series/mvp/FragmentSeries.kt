package rmnvich.apps.kinonika.presentation.fragment.series.mvp

import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.miguelcatalan.materialsearchview.MaterialSearchView
import rmnvich.apps.kinonika.R
import rmnvich.apps.kinonika.app.App
import rmnvich.apps.kinonika.data.common.Constants.*
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.databinding.FragmentSeriesBinding
import rmnvich.apps.kinonika.presentation.activity.home.HomeActivity
import rmnvich.apps.kinonika.presentation.adapter.MovieAdapter
import rmnvich.apps.kinonika.presentation.custom.BaseBackPressedListener
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

        (activity as HomeActivity).setSupportActionBar(binding.toolbar)
        (activity as HomeActivity).setOnBackPressedListener(object :
                BaseBackPressedListener(activity as FragmentActivity) {
            override fun doBack() {
                if (binding.searchView.isSearchOpen) {
                    binding.searchView.closeSearch()
                    return
                }
                super.doBack()
            }
        })
        setHasOptionsMenu(true)

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

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        menu?.clear()
        inflater?.inflate(R.menu.search_menu, menu)
        val item = menu?.findItem(R.id.action_search)

        binding.searchView.clearFocus()
        binding.searchView.setMenuItem(item)
        binding.searchView.setBackgroundColor(Color.BLACK)
        binding.searchView.setBackIcon(resources.getDrawable(R.drawable.ic_action_back_inverted))
        binding.searchView.setCloseIcon(resources.getDrawable(R.drawable.ic_action_close_inverted))
        binding.searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                mAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                mAdapter.filter.filter(newText)
                return false
            }
        })
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
        binding.progressBar.show()
    }

    override fun hideProgress() {
        binding.progressBar.hide()
    }

    override fun onPause() {
        super.onPause()
        binding.searchView.closeSearch()
    }

    override fun onDetach() {
        super.onDetach()
        App.getApp(activity?.applicationContext)
                .componentsHolder.releaseComponent(javaClass)
        mPresenter.detachView()
    }
}