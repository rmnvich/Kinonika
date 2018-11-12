package rmnvich.apps.kinonika.presentation.activity.make.dagger

import dagger.Subcomponent
import rmnvich.apps.kinonika.data.di.base.BaseComponent
import rmnvich.apps.kinonika.data.di.base.BaseComponentBuilder
import rmnvich.apps.kinonika.presentation.activity.make.mvp.MakeReviewActivity

@PerMakeReviewActivity
@Subcomponent(modules = [MakeReviewActivityModule::class])
interface MakeReviewActivityComponent : BaseComponent<MakeReviewActivity> {

    @Subcomponent.Builder
    interface Builder : BaseComponentBuilder<MakeReviewActivityComponent, MakeReviewActivityModule>
}