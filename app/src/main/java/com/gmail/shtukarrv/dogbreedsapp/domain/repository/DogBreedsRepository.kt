package com.gmail.shtukarrv.dogbreedsapp.domain.repository

import com.gmail.shtukarrv.dogbreedsapp.domain.entity.DogBreed
import kotlinx.coroutines.flow.Flow

interface DogBreedsRepository {
    suspend fun getDogBreeds(): Flow<List<DogBreed>>
}
