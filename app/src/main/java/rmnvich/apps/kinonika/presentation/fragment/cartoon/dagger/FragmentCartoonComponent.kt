package rmnvich.apps.kinonika.presentation.fragment.cartoon.dagger

import dagger.Subcomponent
import rmnvich.apps.kinonika.data.di.base.BaseComponent
import rmnvich.apps.kinonika.data.di.base.BaseComponentBuilder
import rmnvich.apps.kinonika.presentation.fragment.cartoon.mvp.FragmentCartoon

@PerFragmentCartoon
@Subcomponent(modules = [FragmentCartoonModule::class])
interface FragmentCartoonComponent : BaseComponent<FragmentCartoon> {

    @Subcomponent.Builder
    interface Builder : BaseComponentBuilder<FragmentCartoonComponent, FragmentCartoonModule>
}