package rmnvich.apps.kinonika.presentation.activity.home.dagger

import dagger.Module
import dagger.Provides
import rmnvich.apps.kinonika.data.di.base.BaseModule
import rmnvich.apps.kinonika.presentation.fragment.cartoon.FragmentCartoon
import rmnvich.apps.kinonika.presentation.fragment.film.FragmentFilm
import rmnvich.apps.kinonika.presentation.fragment.series.FragmentSeries
import rmnvich.apps.kinonika.presentation.fragment.tvshow.FragmentTVShow

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