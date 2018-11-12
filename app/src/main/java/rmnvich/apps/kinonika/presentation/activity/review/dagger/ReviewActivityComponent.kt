package rmnvich.apps.kinonika.presentation.activity.review.dagger

import dagger.Subcomponent
import rmnvich.apps.kinonika.data.di.base.BaseComponent
import rmnvich.apps.kinonika.data.di.base.BaseComponentBuilder
import rmnvich.apps.kinonika.presentation.activity.review.mvp.ViewReviewActivity

@PerReviewActivity
@Subcomponent(modules = [ReviewActivityModule::class])
interface ReviewActivityComponent : BaseComponent<ViewReviewActivity> {

    @Subcomponent.Builder
    interface Builder : BaseComponentBuilder<ReviewActivityComponent, ReviewActivityModule>
}