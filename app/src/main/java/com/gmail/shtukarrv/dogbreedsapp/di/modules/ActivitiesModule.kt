package com.gmail.shtukarrv.dogbreedsapp.di.modules

import com.gmail.shtukarrv.dogbreedsapp.presentation.ui.MainActivity
import com.gmail.shtukarrv.dogbreedsapp.presentation.ui.splash.SplashScreenActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector
    abstract fun getSplashScreenActivity(): SplashScreenActivity

    @ContributesAndroidInjector
    abstract fun getMainActivity(): MainActivity
}
