package rmnvich.apps.kinonika.presentation.fragment.cartoon.mvp

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import rmnvich.apps.kinonika.R
import rmnvich.apps.kinonika.app.App
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.databinding.FragmentCartoonBinding

class FragmentCartoon : Fragment(), FragmentCartoonContract.View {

    private lateinit var binding : FragmentCartoonBinding

    private lateinit var mPresenter : FragmentCartoonPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cartoon, container, false)
        binding.handler = this

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