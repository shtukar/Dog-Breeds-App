package com.gmail.shtukarrv.dogbreedsapp.di

import com.gmail.shtukarrv.dogbreedsapp.App
import com.gmail.shtukarrv.dogbreedsapp.di.modules.*
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        FragmentsModule::class,
        ActivitiesModule::class,
        ViewModelModule::class,
        UseCaseModule::class,
        RepositoryModule::class,
        NetworkModule::class,
        PersistenceModule::class
    ]
)

interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<App>
}