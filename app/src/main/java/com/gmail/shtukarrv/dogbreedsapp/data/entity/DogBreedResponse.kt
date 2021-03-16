package com.gmail.shtukarrv.dogbreedsapp.data.entity

import com.gmail.shtukarrv.dogbreedsapp.domain.entity.DogBreed
import com.gmail.shtukarrv.dogbreedsapp.domain.entity.DogSubBreed

data class DogBreedResponse(val status: String, val message: Map<String, List<String>>) {

    fun toDogBreedList(): List<DogBreed> {
        return message.entries.map {
            val subBreed = it.value.map { sub ->
                DogSubBreed(sub)
            }
            DogBreed(it.key, subBreed)
        }
    }
}