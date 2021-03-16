package com.gmail.shtukarrv.dogbreedsapp.domain.repository

import com.gmail.shtukarrv.dogbreedsapp.domain.entity.DogBreedImage
import kotlinx.coroutines.flow.Flow

interface DogBreedImagesRepository {

    suspend fun getDogBreedImages(breedName: String): Flow<List<DogBreedImage>>

    suspend fun getDogSubBreedImages(breedName: String, subBreedName: String):
            Flow<List<DogBreedImage>>
}
