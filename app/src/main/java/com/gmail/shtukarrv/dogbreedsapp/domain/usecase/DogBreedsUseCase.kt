package com.gmail.shtukarrv.dogbreedsapp.domain.usecase

import com.gmail.shtukarrv.dogbreedsapp.domain.entity.DogBreed
import kotlinx.coroutines.flow.Flow

interface DogBreedsUseCase {
    suspend fun getDogBreeds(): Flow<List<DogBreed>>
}
