package rmnvich.apps.kinonika.presentation.activity.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import rmnvich.apps.kinonika.R
import rmnvich.apps.kinonika.databinding.ActivityHomeBinding
import rmnvich.apps.kinonika.presentation.custom.OnBackPressedListener
import rmnvich.apps.kinonika.presentation.fragment.cartoon.FragmentCartoon
import rmnvich.apps.kinonika.presentation.fragment.film.FragmentFilm
import rmnvich.apps.kinonika.presentation.fragment.series.FragmentSeries
import rmnvich.apps.kinonika.presentation.fragment.tvshow.FragmentTVShow
import javax.inject.Inject


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val mFragmentManager = supportFragmentManager
    private var mActiveFragment: Fragment? = null

    private var onBackPressedListener: OnBackPressedListener? = null

    fun setOnBackPressedListener(onBackPressedListener: OnBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.handler = this
        init()
    }

    @Inject
    fun init() {
        mActiveFragment = FragmentFilm.newInstance()
        showFragment()

        binding.bottomNavigationView.enableShiftingMode(false)
        binding.bottomNavigationView.onNavigationItemSelectedListener =
                BottomNavigationView.OnNavigationItemSelectedListener {
                    when (it.itemId) {
                        R.id.nav_films -> {
                            if (mActiveFragment !is FragmentFilm) {
                                mActiveFragment = FragmentFilm.newInstance()
                                showFragment()
                            }
                        }
                        R.id.nav_series -> {
                            if (mActiveFragment !is FragmentSeries) {
                                mActiveFragment = FragmentSeries.newInstance()
                                showFragment()
                            }
                        }
                        R.id.nav_tvshow -> {
                            if (mActiveFragment !is FragmentTVShow) {
                                mActiveFragment = FragmentTVShow.newInstance()
                                showFragment()
                            }
                        }
                        R.id.nav_cartoons -> {
                            if (mActiveFragment !is FragmentCartoon) {
                                mActiveFragment = FragmentCartoon.newInstance()
                                showFragment()
                            }
                        }
                        else -> FragmentFilm.newInstance()
                    }
                    true
                }
    }

    fun showProgress() {
        binding.progressBar.smoothToShow()
    }

    fun hideProgress() {
        binding.progressBar.smoothToHide()
    }

    private fun showFragment() {
        mFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.content, mActiveFragment)
                .commit()
    }

    override fun onBackPressed() {
        if (onBackPressedListener != null)
            onBackPressedListener?.doBack()
        else super.onBackPressed()
    }
}
