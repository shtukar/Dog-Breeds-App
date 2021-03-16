package com.gmail.shtukarrv.dogbreedsapp.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A mapping from DogBreedResponse class. The set of params supported by this class
 * is purposefully restricted to the ones that can be used on UI.
 * @property name  the name of dog breed
 * @property subBreed list of dog sub-breeds
 */
@Entity
data class DogBreed(
    val name: String,
    val subBreed: List<DogSubBreed>,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)

data class DogSubBreed(
    val name: String
)
