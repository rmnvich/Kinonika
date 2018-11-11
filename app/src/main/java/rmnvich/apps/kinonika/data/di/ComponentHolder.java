package rmnvich.apps.kinonika.data.di;


import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import rmnvich.apps.kinonika.app.dagger.AppComponent;
import rmnvich.apps.kinonika.app.dagger.AppModule;
import rmnvich.apps.kinonika.app.dagger.DaggerAppComponent;
import rmnvich.apps.kinonika.data.di.base.BaseComponent;
import rmnvich.apps.kinonika.data.di.base.BaseComponentBuilder;
import rmnvich.apps.kinonika.data.di.base.BaseModule;

public class ComponentHolder {

    private final Context mContext;

    @Inject
    Map<Class<?>, Provider<BaseComponentBuilder>> mBuilders;

    private Map<Class<?>, BaseComponent> mComponents;

    public ComponentHolder(Context context) {
        mContext = context;
    }

    public void init() {
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(mContext)).build();
        appComponent.injectComponentsHolder(this);
        mComponents = new HashMap<>();
    }

    public BaseComponent getComponent(Class<?> cls) {
        return getComponent(cls, null);
    }

    public BaseComponent getComponent(Class<?> cls, BaseModule module) {
        BaseComponent component = mComponents.get(cls);
        if (component == null) {
            BaseComponentBuilder builder = mBuilders.get(cls).get();
            if (module != null) {
                builder.module(module);
            }
            component = builder.build();
            mComponents.put(cls, component);
        }
        return component;
    }

    public void releaseComponent(Class<?> cls) {
        mComponents.put(cls, null);
    }
}
