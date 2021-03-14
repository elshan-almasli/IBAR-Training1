package com.example.javacourcecommonapp.di;

import android.app.Application;

import com.example.javacourcecommonapp.BaseApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(
        modules = {
                AndroidInjectionModule.class,
                ActivityBuilderModule.class,
                AppModule.class,
                DatabaseModule.class,
                NetworkModule.class,
                RepositoryModule.class,
                ViewModelFactoryModule.class
        }
)
public interface AppComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder{

        @BindsInstance
        Builder app(Application application);

        AppComponent build();

    }

}
