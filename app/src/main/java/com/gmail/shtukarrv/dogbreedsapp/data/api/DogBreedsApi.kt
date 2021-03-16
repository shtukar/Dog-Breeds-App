package com.gmail.shtukarrv.dogbreedsapp.data.api

import com.gmail.shtukarrv.dogbreedsapp.data.entity.DogBreedImageResponse
import com.gmail.shtukarrv.dogbreedsapp.data.entity.DogBreedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * The interface which provides methods to get result of base currency webservices
 */
interface DogBreedsApi {

    @GET("breeds/list/all/")
    suspend fun getDogBreeds(): Response<DogBreedResponse>

    @GET("breed/{breed_name}/images/")
    suspend fun getDogBreedImages(@Path("breed_name") breedName: String): Response<DogBreedImageResponse>

    @GET("breed/{breed_name}/{sub_breed_name}/images/")
    suspend fun getDogSubBreedImages(
        @Path("breed_name") breedName: String,
        @Path("sub_breed_name") subBreedName: String
    ): Response<DogBreedImageResponse>

}
