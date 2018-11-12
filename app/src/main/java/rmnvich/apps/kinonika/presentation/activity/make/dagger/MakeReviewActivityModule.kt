package rmnvich.apps.kinonika.presentation.activity.make.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import rmnvich.apps.kinonika.data.di.base.BaseModule
import rmnvich.apps.kinonika.data.repository.database.DatabaseRepository
import rmnvich.apps.kinonika.data.repository.local.LocalRepository
import rmnvich.apps.kinonika.presentation.activity.make.mvp.MakeReviewActivityModel
import rmnvich.apps.kinonika.presentation.activity.make.mvp.MakeReviewActivityPresenter

@Module
class MakeReviewActivityModule(val context: Context) : BaseModule {

    @Provides
    @PerMakeReviewActivity
    fun providePresenter(compositeDisposable: CompositeDisposable,
                         model: MakeReviewActivityModel): MakeReviewActivityPresenter {
        return MakeReviewActivityPresenter(compositeDisposable, model)
    }

    @Provides
    @PerMakeReviewActivity
    fun provideCompositeDisposable() : CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    @PerMakeReviewActivity
    fun provideModel(databaseRepository: DatabaseRepository,
                     localRepository: LocalRepository) : MakeReviewActivityModel {
        return MakeReviewActivityModel(databaseRepository, localRepository)
    }

    @Provides
    @PerMakeReviewActivity
    fun provideLocalRepository() : LocalRepository {
        return LocalRepository(context)
    }

}