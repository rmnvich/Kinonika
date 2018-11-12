package rmnvich.apps.kinonika.presentation.activity.review.dagger

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import rmnvich.apps.kinonika.data.di.base.BaseModule
import rmnvich.apps.kinonika.data.repository.database.DatabaseRepository
import rmnvich.apps.kinonika.presentation.activity.review.mvp.ReviewActivityModel
import rmnvich.apps.kinonika.presentation.activity.review.mvp.ReviewActivityPresenter

@Module
class ReviewActivityModule : BaseModule {

    @Provides
    @PerReviewActivity
    fun providePresenter(compositeDisposable: CompositeDisposable,
                         model: ReviewActivityModel): ReviewActivityPresenter {
        return ReviewActivityPresenter(compositeDisposable, model)
    }

    @Provides
    @PerReviewActivity
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    @PerReviewActivity
    fun provideModel(databaseRepository: DatabaseRepository): ReviewActivityModel {
        return ReviewActivityModel(databaseRepository)
    }
}