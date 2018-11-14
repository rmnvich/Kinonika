package rmnvich.apps.kinonika.presentation.fragment.tvshow.dagger

import dagger.Subcomponent
import rmnvich.apps.kinonika.data.di.base.BaseComponent
import rmnvich.apps.kinonika.data.di.base.BaseComponentBuilder
import rmnvich.apps.kinonika.presentation.fragment.tvshow.FragmentTVShow

@PerFragmentTVShow
@Subcomponent(modules = [FragmentTVShowModule::class])
interface FragmentTVShowComponent : BaseComponent<FragmentTVShow> {

    @Subcomponent.Builder
    interface Builder : BaseComponentBuilder<FragmentTVShowComponent, FragmentTVShowModule>
}