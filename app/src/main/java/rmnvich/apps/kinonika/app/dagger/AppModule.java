package rmnvich.apps.kinonika.app.dagger;

import android.arch.persistence.room.Room;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import rmnvich.apps.kinonika.data.di.base.BaseComponentBuilder;
import rmnvich.apps.kinonika.data.repository.database.utils.AppDatabase;
import rmnvich.apps.kinonika.presentation.activity.home.dagger.HomeActivityComponent;
import rmnvich.apps.kinonika.presentation.activity.home.mvp.HomeActivity;

@Module(subcomponents = {HomeActivityComponent.class})
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

}
