package rmnvich.apps.kinonika.presentation.fragment.series.dagger

import dagger.Subcomponent
import rmnvich.apps.kinonika.data.di.base.BaseComponent
import rmnvich.apps.kinonika.data.di.base.BaseComponentBuilder
import rmnvich.apps.kinonika.presentation.fragment.series.FragmentSeries

@PerFragmentSeries
@Subcomponent(modules = [FragmentSeriesModule::class])
interface FragmentSeriesComponent : BaseComponent<FragmentSeries> {

    @Subcomponent.Builder
    interface Builder : BaseComponentBuilder<FragmentSeriesComponent, FragmentSeriesModule>
}