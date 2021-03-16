package com.gmail.shtukarrv.dogbreedsapp.di.modules

import com.gmail.shtukarrv.dogbreedsapp.data.api.DogBreedsApi
import com.gmail.shtukarrv.dogbreedsapp.data.local.DogBreedsDao
import com.gmail.shtukarrv.dogbreedsapp.data.repository.DogBreedImagesRepositoryImpl
import com.gmail.shtukarrv.dogbreedsapp.data.repository.DogBreedsRepositoryImpl
import com.gmail.shtukarrv.dogbreedsapp.domain.repository.DogBreedImagesRepository
import com.gmail.shtukarrv.dogbreedsapp.domain.repository.DogBreedsRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun providesDogBreedsRepository(api: DogBreedsApi, database: DogBreedsDao):
            DogBreedsRepository = DogBreedsRepositoryImpl(api, database)

    @Provides
    fun providesDogBreedImagesRepository(api: DogBreedsApi): DogBreedImagesRepository =
        DogBreedImagesRepositoryImpl(api)


}