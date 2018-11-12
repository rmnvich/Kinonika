package rmnvich.apps.kinonika.presentation.fragment.series.dagger

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import rmnvich.apps.kinonika.data.di.base.BaseModule
import rmnvich.apps.kinonika.data.repository.database.DatabaseRepository
import rmnvich.apps.kinonika.presentation.adapter.MovieAdapter
import rmnvich.apps.kinonika.presentation.fragment.cartoon.dagger.PerFragmentCartoon
import rmnvich.apps.kinonika.presentation.fragment.series.mvp.FragmentSeriesModel
import rmnvich.apps.kinonika.presentation.fragment.series.mvp.FragmentSeriesPresenter

@Module
class FragmentSeriesModule : BaseModule {

    @PerFragmentSeries
    @Provides
    fun providePresenter(compositeDisposable: CompositeDisposable, model: FragmentSeriesModel)
            : FragmentSeriesPresenter {
        return FragmentSeriesPresenter(compositeDisposable, model)
    }

    @PerFragmentSeries
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @PerFragmentSeries
    @Provides
    fun provideModel(databaseRepository: DatabaseRepository): FragmentSeriesModel {
        return FragmentSeriesModel(databaseRepository)
    }

    @PerFragmentSeries
    @Provides
    fun provideAdapter(): MovieAdapter {
        return MovieAdapter()
    }
}