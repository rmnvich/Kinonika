package rmnvich.apps.kinonika.presentation.fragment.cartoon.dagger

import dagger.Subcomponent
import rmnvich.apps.kinonika.data.di.base.BaseComponent
import rmnvich.apps.kinonika.data.di.base.BaseComponentBuilder
import rmnvich.apps.kinonika.presentation.activity.home.dagger.PerHomeActivity
import rmnvich.apps.kinonika.presentation.fragment.cartoon.mvp.FragmentCartoon

@PerHomeActivity
@Subcomponent(modules = [FragmentCartoonModule::class])
class FragmentCartoonComponent : BaseComponent<FragmentCartoon> {

    override fun inject(view: FragmentCartoon?) {}

    @Subcomponent.Builder
    interface Builder : BaseComponentBuilder<FragmentCartoonComponent, FragmentCartoonModule>
}