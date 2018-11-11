package rmnvich.apps.kinonika.presentation.fragment.tvshow.dagger

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import rmnvich.apps.kinonika.data.di.base.BaseModule
import rmnvich.apps.kinonika.data.repository.database.DatabaseRepository
import rmnvich.apps.kinonika.presentation.fragment.tvshow.mvp.FragmentTVShowModel
import rmnvich.apps.kinonika.presentation.fragment.tvshow.mvp.FragmentTVShowPresenter

@Module
class FragmentTVShowModule: BaseModule {

    @PerFragmentTVShow
    @Provides
    fun providePresenter(compositeDisposable: CompositeDisposable, model: FragmentTVShowModel)
            : FragmentTVShowPresenter {
        return FragmentTVShowPresenter(compositeDisposable, model)
    }

    @PerFragmentTVShow
    @Provides
    fun provideCompositeDisposable() : CompositeDisposable {
        return CompositeDisposable()
    }

    @PerFragmentTVShow
    @Provides
    fun provideModel(databaseRepository: DatabaseRepository) : FragmentTVShowModel {
        return FragmentTVShowModel(databaseRepository)
    }
}