package rmnvich.apps.kinonika.presentation.activity.home.dagger

import dagger.Subcomponent
import rmnvich.apps.kinonika.data.di.base.BaseComponent
import rmnvich.apps.kinonika.data.di.base.BaseComponentBuilder
import rmnvich.apps.kinonika.presentation.activity.home.HomeActivity

@PerHomeActivity
@Subcomponent(modules = [HomeActivityModule::class])
interface HomeActivityComponent : BaseComponent<HomeActivity> {

    @Subcomponent.Builder
    interface Builder : BaseComponentBuilder<HomeActivityComponent, HomeActivityModule>
}
