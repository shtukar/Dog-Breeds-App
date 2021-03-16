package com.gmail.shtukarrv.dogbreedsapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gmail.shtukarrv.dogbreedsapp.di.qualifier.ViewModelKey
import com.gmail.shtukarrv.dogbreedsapp.presentation.ui.dog_breed_images.DogBreedImagesViewModel
import com.gmail.shtukarrv.dogbreedsapp.presentation.ui.dog_breeds_list.DogBreedsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(DogBreedsListViewModel::class)
    internal abstract fun bindDogBreedsListViewModel(viewModel: DogBreedsListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DogBreedImagesViewModel::class)
    internal abstract fun bindDogBreedImagesViewModel(viewModel: DogBreedImagesViewModel): ViewModel
}
