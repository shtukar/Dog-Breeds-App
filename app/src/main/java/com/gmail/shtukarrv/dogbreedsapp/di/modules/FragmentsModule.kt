package com.gmail.shtukarrv.dogbreedsapp.di.modules

import com.gmail.shtukarrv.dogbreedsapp.presentation.ui.dog_breed_images.DogBreedImagesListFragment
import com.gmail.shtukarrv.dogbreedsapp.presentation.ui.dog_breeds_list.DogBreedsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun getDogBreedsListFragment(): DogBreedsListFragment

    @ContributesAndroidInjector
    abstract fun getDogBreedImagesListFragment(): DogBreedImagesListFragment
}
