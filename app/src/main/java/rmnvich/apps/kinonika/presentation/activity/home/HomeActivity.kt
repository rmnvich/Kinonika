package rmnvich.apps.kinonika.presentation.activity.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import rmnvich.apps.kinonika.R
import rmnvich.apps.kinonika.app.App
import rmnvich.apps.kinonika.databinding.ActivityHomeBinding
import rmnvich.apps.kinonika.presentation.fragment.cartoon.mvp.FragmentCartoon
import rmnvich.apps.kinonika.presentation.fragment.film.mvp.FragmentFilm
import rmnvich.apps.kinonika.presentation.fragment.series.mvp.FragmentSeries
import rmnvich.apps.kinonika.presentation.fragment.tvshow.mvp.FragmentTVShow
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    @Inject
    lateinit var mFragmentFilm: FragmentFilm

    @Inject
    lateinit var mFragmentSeries: FragmentSeries

    @Inject
    lateinit var mFragmentTVShow: FragmentTVShow

    @Inject
    lateinit var mFragmentCartoon: FragmentCartoon

    private val mFragmentManager = supportFragmentManager
    private var mActiveFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.handler = this

        App.getApp(this).componentsHolder
            .getComponent(javaClass).inject(this)
    }

    @Inject
    fun init() {
        mActiveFragment = mFragmentFilm
        showFragment()

        binding.bottomNavigationView.onNavigationItemSelectedListener =
                BottomNavigationView.OnNavigationItemSelectedListener {
                    mActiveFragment = when (it.itemId) {
                        R.id.nav_films -> mFragmentFilm
                        R.id.nav_series -> mFragmentSeries
                        R.id.nav_tvshow -> mFragmentTVShow
                        else -> mFragmentCartoon
                    }
                    showFragment()
                    true
                }
    }

    private fun showFragment() {
        mFragmentManager.beginTransaction()
            .replace(R.id.content, mActiveFragment)
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) {
            App.getApp(this).componentsHolder
                .releaseComponent(javaClass)
        }
    }
}
