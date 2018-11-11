package rmnvich.apps.kinonika.presentation.activity.home.dagger

import dagger.Module
import dagger.Provides
import rmnvich.apps.kinonika.data.di.base.BaseModule
import rmnvich.apps.kinonika.presentation.activity.home.mvp.HomeActivityPresenter

@Module
class HomeActivityModule : BaseModule {

    @PerHomeActivity
    @Provides
    fun providePresenter() : HomeActivityPresenter {
        return HomeActivityPresenter()
    }
}