package com.gmail.shtukarrv.dogbreedsapp.data.entity

import com.gmail.shtukarrv.dogbreedsapp.domain.entity.DogBreedImage

data class DogBreedImageResponse(val status: String, val message: List<String>) {

    fun toDogBreedImagesList(): List<DogBreedImage> {
        return message.map {
            DogBreedImage(imageUrl = it)
        }
    }
}
