package rmnvich.apps.kinonika.presentation.fragment.film.dagger

import dagger.Subcomponent
import rmnvich.apps.kinonika.data.di.base.BaseComponent
import rmnvich.apps.kinonika.data.di.base.BaseComponentBuilder
import rmnvich.apps.kinonika.presentation.fragment.film.mvp.FragmentFilm

@PerFragmentFilm
@Subcomponent(modules = [FragmentFilmModule::class])
interface FragmentFilmComponent : BaseComponent<FragmentFilm> {

    @Subcomponent.Builder
    interface Builder : BaseComponentBuilder<FragmentFilmComponent, FragmentFilmModule>
}
