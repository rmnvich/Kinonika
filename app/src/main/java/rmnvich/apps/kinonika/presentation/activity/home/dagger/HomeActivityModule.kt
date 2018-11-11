package rmnvich.apps.kinonika.presentation.activity.home.dagger

import dagger.Module
import dagger.Provides
import rmnvich.apps.kinonika.data.di.base.BaseModule
import rmnvich.apps.kinonika.presentation.fragment.cartoon.mvp.FragmentCartoon
import rmnvich.apps.kinonika.presentation.fragment.film.mvp.FragmentFilm
import rmnvich.apps.kinonika.presentation.fragment.series.mvp.FragmentSeries
import rmnvich.apps.kinonika.presentation.fragment.tvshow.mvp.FragmentTVShow

@Module
class HomeActivityModule : BaseModule {

    @Provides
    @PerHomeActivity
    fun provideFragmentFilm(): FragmentFilm {
        return FragmentFilm.newInstance()
    }

    @Provides
    @PerHomeActivity
    fun provideFragmentSeries(): FragmentSeries {
        return FragmentSeries.newInstance()
    }

    @Provides
    @PerHomeActivity
    fun provideFragmentTVShow(): FragmentTVShow {
        return FragmentTVShow.newInstance()
    }

    @Provides
    @PerHomeActivity
    fun provideFragmentCartoon(): FragmentCartoon {
        return FragmentCartoon.newInstance()
    }

}