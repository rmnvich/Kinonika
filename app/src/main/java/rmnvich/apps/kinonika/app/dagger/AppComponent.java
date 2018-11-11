package rmnvich.apps.kinonika.app.dagger;


import dagger.Component;
import rmnvich.apps.kinonika.data.di.ComponentHolder;

@PerApplication
@Component(modules = AppModule.class)
public interface AppComponent {
    void injectComponentsHolder(ComponentHolder holder);
}
