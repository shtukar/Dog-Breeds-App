package com.gmail.shtukarrv.dogbreedsapp.data.repository

import com.gmail.shtukarrv.dogbreedsapp.data.ApiConstants.SUCCESS_STATUS
import com.gmail.shtukarrv.dogbreedsapp.data.FetchDataException
import com.gmail.shtukarrv.dogbreedsapp.data.ServerErrorException
import com.gmail.shtukarrv.dogbreedsapp.data.api.DogBreedsApi
import com.gmail.shtukarrv.dogbreedsapp.data.local.DogBreedsDao
import com.gmail.shtukarrv.dogbreedsapp.domain.entity.DogBreed
import com.gmail.shtukarrv.dogbreedsapp.domain.repository.DogBreedsRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DogBreedsRepositoryImpl(
    private val api: DogBreedsApi,
    private val database: DogBreedsDao
) : DogBreedsRepository {

    override suspend fun getDogBreeds(): Flow<List<DogBreed>> = coroutineScope {
        launch {
            api.getDogBreeds()
                .also {
                    when (it.isSuccessful) {
                        true -> {
                            val result = it.body()
                            if (result != null && result.status == SUCCESS_STATUS) {
                                val dogBreedList = result.toDogBreedList()
                                database.saveDogBreedsList(dogBreedList)
                            } else {
                                throw FetchDataException
                            }
                        }
                        false -> throw ServerErrorException(
                            it.code(),
                            it.errorBody()?.string()
                        )
                    }
                }
        }

        database.getAllDogBreedsFlow()
    }
}
