package rmnvich.apps.kinonika.presentation.fragment.cartoon.dagger

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import rmnvich.apps.kinonika.data.di.base.BaseModule
import rmnvich.apps.kinonika.data.repository.database.DatabaseRepository
import rmnvich.apps.kinonika.presentation.adapter.MovieAdapter
import rmnvich.apps.kinonika.presentation.fragment.cartoon.mvp.FragmentCartoonModel
import rmnvich.apps.kinonika.presentation.fragment.cartoon.mvp.FragmentCartoonPresenter

@Module
class FragmentCartoonModule : BaseModule {

    @PerFragmentCartoon
    @Provides
    fun providePresenter(compositeDisposable: CompositeDisposable, model: FragmentCartoonModel)
            : FragmentCartoonPresenter {
        return FragmentCartoonPresenter(compositeDisposable, model)
    }

    @PerFragmentCartoon
    @Provides
    fun provideCompositeDisposable() : CompositeDisposable {
        return CompositeDisposable()
    }

    @PerFragmentCartoon
    @Provides
    fun provideModel(databaseRepository: DatabaseRepository) : FragmentCartoonModel {
        return FragmentCartoonModel(databaseRepository)
    }

    @PerFragmentCartoon
    @Provides
    fun provideAdapter() : MovieAdapter {
        return MovieAdapter()
    }
}