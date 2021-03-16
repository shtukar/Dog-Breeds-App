package com.gmail.shtukarrv.dogbreedsapp.domain.usecase

import com.gmail.shtukarrv.dogbreedsapp.domain.entity.DogBreedImage
import com.gmail.shtukarrv.dogbreedsapp.domain.repository.DogBreedImagesRepository
import kotlinx.coroutines.flow.Flow

class DogBreedImagesUseCaseImpl(private val repository: DogBreedImagesRepository) :
    DogBreedImagesUseCase {

    override suspend fun getDogBreedImages(breedName: String): Flow<List<DogBreedImage>> =
        repository.getDogBreedImages(breedName)

    override suspend fun getDogSubBreedImages(breedName: String, subBreedName: String):
            Flow<List<DogBreedImage>> =
        repository.getDogSubBreedImages(breedName, subBreedName)
}
