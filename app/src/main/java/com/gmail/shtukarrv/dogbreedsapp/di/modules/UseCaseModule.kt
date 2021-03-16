package com.gmail.shtukarrv.dogbreedsapp.di.modules

import com.gmail.shtukarrv.dogbreedsapp.domain.repository.DogBreedImagesRepository
import com.gmail.shtukarrv.dogbreedsapp.domain.repository.DogBreedsRepository
import com.gmail.shtukarrv.dogbreedsapp.domain.usecase.DogBreedImagesUseCase
import com.gmail.shtukarrv.dogbreedsapp.domain.usecase.DogBreedImagesUseCaseImpl
import com.gmail.shtukarrv.dogbreedsapp.domain.usecase.DogBreedsUseCase
import com.gmail.shtukarrv.dogbreedsapp.domain.usecase.DogBreedsUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun providesDogBreedsUseCase(repository: DogBreedsRepository): DogBreedsUseCase =
        DogBreedsUseCaseImpl(repository)

    @Provides
    fun providesDogBreedImagesUseCase(repository: DogBreedImagesRepository): DogBreedImagesUseCase =
        DogBreedImagesUseCaseImpl(repository)
}