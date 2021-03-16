package com.gmail.shtukarrv.dogbreedsapp.domain.usecase

import com.gmail.shtukarrv.dogbreedsapp.domain.entity.DogBreed
import com.gmail.shtukarrv.dogbreedsapp.domain.repository.DogBreedsRepository
import kotlinx.coroutines.flow.Flow

class DogBreedsUseCaseImpl(private val repository: DogBreedsRepository) : DogBreedsUseCase {

    override suspend fun getDogBreeds(): Flow<List<DogBreed>> =
        repository.getDogBreeds()
}
