package rmnvich.apps.kinonika.presentation.fragment.cartoon.mvp

import android.annotation.SuppressLint
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.miguelcatalan.materialsearchview.MaterialSearchView
import rmnvich.apps.kinonika.R
import rmnvich.apps.kinonika.app.App
import rmnvich.apps.kinonika.data.common.Constants.REQUEST_CODE_CARTOON
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.databinding.FragmentCartoonBinding
import rmnvich.apps.kinonika.presentation.activity.home.HomeActivity
import rmnvich.apps.kinonika.presentation.adapter.MovieAdapter
import rmnvich.apps.kinonika.presentation.custom.BaseBackPressedListener
import rmnvich.apps.kinonika.presentation.dialog.DialogFilter
import rmnvich.apps.kinonika.presentation.fragment.cartoon.dagger.FragmentCartoonModule
import rmnvich.apps.kinonika.presentation.fragment.film.mvp.FragmentMovieContract
import javax.inject.Inject
import javax.inject.Provider

class FragmentCartoon : Fragment(), FragmentMovieContract.View {

    private lateinit var binding: FragmentCartoonBinding

    @Inject
    lateinit var mPresenter: FragmentCartoonPresenter

    @Inject
    lateinit var mAdapter: MovieAdapter

    @Inject
    lateinit var mFilterDialog: Provider<DialogFilter>

    companion object {
        fun newInstance(): FragmentCartoon {
            return FragmentCartoon()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cartoon, container, false)
        binding.handler = this

        (activity as HomeActivity).setSupportActionBar(binding.toolbar)
        (activity as HomeActivity).setOnBackPressedListener(object :
                BaseBackPressedListener(activity as FragmentActivity) {
            override fun doBack() {
                if (binding.searchView.isSearchOpen) {
                    binding.searchView.closeSearch()
                    return
                } else activity?.finish()
                super.doBack()
            }
        })
        setHasOptionsMenu(true)

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

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search_menu, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val filterItem = menu?.findItem(R.id.action_filter)

        binding.searchView.setMenuItem(searchItem)
        binding.searchView.setOnQueryTextListener(object :
                MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                mAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                mAdapter.filter.filter(newText)
                return false
            }
        })

        filterItem?.setOnMenuItemClickListener {
            mPresenter.onClickFilter()
            true
        }
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
                .getComponent(javaClass, FragmentCartoonModule(context!!))
                .inject(this)
    }

    override fun updateAdapter(movies: List<Movie>) {
        mAdapter.setData(movies)
    }

    override fun setAnimationTypeToAdapter(position: Int, animationType: Int) {
        mAdapter.setActionType(animationType)
        mAdapter.setPosition(position)
    }

    override fun showFilterDialog(tags: List<String>) {
        mFilterDialog.get().show({ genre, tag, rating, year ->
            mPresenter.onFilterApply(genre, tag, rating, year)
        }, tags)
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