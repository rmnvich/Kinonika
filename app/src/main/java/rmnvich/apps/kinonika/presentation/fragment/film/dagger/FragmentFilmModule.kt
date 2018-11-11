package rmnvich.apps.kinonika.presentation.fragment.film.dagger

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import rmnvich.apps.kinonika.data.di.base.BaseModule
import rmnvich.apps.kinonika.data.repository.database.DatabaseRepository
import rmnvich.apps.kinonika.presentation.fragment.film.mvp.FragmentFilmModel
import rmnvich.apps.kinonika.presentation.fragment.film.mvp.FragmentFilmPresenter

@Module
class FragmentFilmModule : BaseModule {

    @PerFragmentFilm
    @Provides
    fun providePresenter(compositeDisposable: CompositeDisposable, model: FragmentFilmModel)
            : FragmentFilmPresenter {
        return FragmentFilmPresenter(compositeDisposable, model)
    }

    @PerFragmentFilm
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @PerFragmentFilm
    @Provides
    fun provideModel(databaseRepository: DatabaseRepository): FragmentFilmModel {
        return FragmentFilmModel(databaseRepository)
    }
}