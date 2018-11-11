package rmnvich.apps.kinonika.app;

import android.app.Application;
import android.content.Context;

import rmnvich.apps.kinonika.data.di.ComponentHolder;


public class App extends Application {

    private ComponentHolder componentsHolder;

    public static App getApp(Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        componentsHolder = new ComponentHolder(this);
        componentsHolder.init();
    }

    public ComponentHolder getComponentsHolder() {
        return componentsHolder;
    }
}
