package rmnvich.apps.kinonika.app.dagger;

import android.arch.persistence.room.Room;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import rmnvich.apps.kinonika.data.di.base.BaseComponentBuilder;
import rmnvich.apps.kinonika.data.repository.database.DatabaseRepository;
import rmnvich.apps.kinonika.data.repository.database.utils.AppDatabase;
import rmnvich.apps.kinonika.presentation.activity.home.dagger.HomeActivityComponent;
import rmnvich.apps.kinonika.presentation.activity.home.HomeActivity;
import rmnvich.apps.kinonika.presentation.fragment.cartoon.dagger.FragmentCartoonComponent;
import rmnvich.apps.kinonika.presentation.fragment.cartoon.mvp.FragmentCartoon;
import rmnvich.apps.kinonika.presentation.fragment.film.dagger.FragmentFilmComponent;
import rmnvich.apps.kinonika.presentation.fragment.film.mvp.FragmentFilm;
import rmnvich.apps.kinonika.presentation.fragment.series.dagger.FragmentSeriesComponent;
import rmnvich.apps.kinonika.presentation.fragment.series.mvp.FragmentSeries;
import rmnvich.apps.kinonika.presentation.fragment.tvshow.dagger.FragmentTVShowComponent;
import rmnvich.apps.kinonika.presentation.fragment.tvshow.mvp.FragmentTVShow;

@Module(subcomponents = {HomeActivityComponent.class, FragmentFilmComponent.class, FragmentSeriesComponent.class,
        FragmentTVShowComponent.class, FragmentCartoonComponent.class})
public class AppModule {

    private final Context mContext;

    public AppModule(Context context) {
        mContext = context;
    }

    @PerApplication
    @Provides
    Context provideContext() {
        return mContext;
    }

    @PerApplication
    @Provides
    DatabaseRepository provideDatabaseRepository(AppDatabase appDatabase) {
        return new DatabaseRepository(appDatabase);
    }

    @Provides
    AppDatabase provideAppDatabase() {
        return Room.databaseBuilder(mContext, AppDatabase.class,
                "testDatabase1").build();
    }

    @Provides
    @IntoMap
    @ClassKey(HomeActivity.class)
    BaseComponentBuilder provideLoginActivity(HomeActivityComponent.Builder builder) {
        return builder;
    }

    @Provides
    @IntoMap
    @ClassKey(FragmentCartoon.class)
    BaseComponentBuilder provideFragmentCartoon(FragmentCartoonComponent.Builder builder) {
        return builder;
    }

    @Provides
    @IntoMap
    @ClassKey(FragmentFilm.class)
    BaseComponentBuilder provideFragmentFilm(FragmentFilmComponent.Builder builder) {
        return builder;
    }

    @Provides
    @IntoMap
    @ClassKey(FragmentSeries.class)
    BaseComponentBuilder provideFragmentSeries(FragmentSeriesComponent.Builder builder) {
        return builder;
    }

    @Provides
    @IntoMap
    @ClassKey(FragmentTVShow.class)
    BaseComponentBuilder provideFragmentTVShow(FragmentTVShowComponent.Builder builder) {
        return builder;
    }

}
