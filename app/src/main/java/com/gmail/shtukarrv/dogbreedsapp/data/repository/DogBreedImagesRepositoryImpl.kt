package com.gmail.shtukarrv.dogbreedsapp.data.repository

import com.gmail.shtukarrv.dogbreedsapp.data.ApiConstants.SUCCESS_STATUS
import com.gmail.shtukarrv.dogbreedsapp.data.FetchDataException
import com.gmail.shtukarrv.dogbreedsapp.data.ServerErrorException
import com.gmail.shtukarrv.dogbreedsapp.data.api.DogBreedsApi
import com.gmail.shtukarrv.dogbreedsapp.domain.entity.DogBreedImage
import com.gmail.shtukarrv.dogbreedsapp.domain.repository.DogBreedImagesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DogBreedImagesRepositoryImpl(private val api: DogBreedsApi) : DogBreedImagesRepository {

    override suspend fun getDogBreedImages(breedName: String): Flow<List<DogBreedImage>> =
        flow {
            val response = api.getDogBreedImages(breedName)
            emit(
                when (response.isSuccessful) {
                    true -> {
                        val result = response.body()
                        if (result != null && result.status == SUCCESS_STATUS) {
                            result.toDogBreedImagesList()
                        } else {
                            throw FetchDataException
                        }
                    }
                    false -> throw ServerErrorException(
                        response.code(),
                        response.errorBody()?.string()
                    )
                }
            )
        }

    override suspend fun getDogSubBreedImages(breedName: String, subBreedName: String):
            Flow<List<DogBreedImage>> =
        flow {
            val response = api.getDogSubBreedImages(breedName, subBreedName)
            emit(
                when (response.isSuccessful) {
                    true -> {
                        val result = response.body()
                        if (result != null && result.status == SUCCESS_STATUS) {
                            result.toDogBreedImagesList()
                        } else {
                            throw FetchDataException
                        }
                    }
                    false -> throw ServerErrorException(
                        response.code(),
                        response.errorBody()?.string()
                    )
                }
            )
        }
}
