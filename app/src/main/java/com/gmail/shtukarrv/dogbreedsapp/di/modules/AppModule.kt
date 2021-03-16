package com.gmail.shtukarrv.dogbreedsapp.di.modules

import android.content.Context
import com.gmail.shtukarrv.dogbreedsapp.App
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun provideApplicationContext(app: App): Context
}
