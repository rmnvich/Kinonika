package rmnvich.apps.kinonika.presentation.fragment.film.dagger

import android.content.Context
import android.widget.ArrayAdapter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import rmnvich.apps.kinonika.R
import rmnvich.apps.kinonika.data.di.base.BaseModule
import rmnvich.apps.kinonika.data.di.qualifier.AdapterGenre
import rmnvich.apps.kinonika.data.di.qualifier.AdapterRating
import rmnvich.apps.kinonika.data.di.qualifier.AdapterYear
import rmnvich.apps.kinonika.data.repository.database.DatabaseRepository
import rmnvich.apps.kinonika.presentation.adapter.MovieAdapter
import rmnvich.apps.kinonika.presentation.dialog.DialogFilter
import rmnvich.apps.kinonika.presentation.fragment.film.mvp.FragmentFilmModel
import rmnvich.apps.kinonika.presentation.fragment.film.mvp.FragmentFilmPresenter
import java.util.*

@Module
class FragmentFilmModule(private val context: Context) : BaseModule {

    @PerFragmentFilm
    @Provides
    fun providePresenter(compositeDisposable: CompositeDisposable, model: FragmentFilmModel)
            : FragmentFilmPresenter {
        return FragmentFilmPresenter(compositeDisposable, model)
    }

    @PerFragmentFilm
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @PerFragmentFilm
    @Provides
    fun provideModel(databaseRepository: DatabaseRepository): FragmentFilmModel {
        return FragmentFilmModel(databaseRepository)
    }

    @PerFragmentFilm
    @Provides
    fun provideAdapter(): MovieAdapter {
        return MovieAdapter()
    }

    @Provides
    fun provideFilterDialog(@AdapterGenre adapterGenre: ArrayAdapter<String>,
                            @AdapterYear adapterYear: ArrayAdapter<String>,
                            @AdapterRating adapterRating: ArrayAdapter<String>): DialogFilter {
        return DialogFilter(context, adapterGenre, adapterRating, adapterYear)
    }

    @Provides
    @AdapterGenre
    fun provideAdapterGenre(): ArrayAdapter<String> {
        return ArrayAdapter(context, R.layout.spinner_item,
                context.resources.getStringArray(R.array.genres))
    }

    @Provides
    @AdapterYear
    fun provideAdapterYear(): ArrayAdapter<String> {
        val years = ArrayList<String>()
        val thisYear = Calendar.getInstance().get(Calendar.YEAR)
        for (i in thisYear downTo 1960) {
            years.add(Integer.toString(i))
        }
        years.add(0, "")
        return ArrayAdapter(context, R.layout.spinner_item, years)
    }

    @Provides
    @AdapterRating
    fun provideAdapterRating(): ArrayAdapter<String> {
        return ArrayAdapter(context, R.layout.spinner_item,
                context.resources.getStringArray(R.array.rating))
    }
}