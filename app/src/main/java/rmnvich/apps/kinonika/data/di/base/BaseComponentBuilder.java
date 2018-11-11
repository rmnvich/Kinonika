package rmnvich.apps.kinonika.data.di.base;


public interface BaseComponentBuilder<C extends BaseComponent, M extends BaseModule> {

    C build();

    BaseComponentBuilder<C, M> module(M module);
}
