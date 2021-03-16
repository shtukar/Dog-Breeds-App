package com.gmail.shtukarrv.dogbreedsapp.presentation.ui.dog_breed_images

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.shtukarrv.dogbreedsapp.domain.entity.DogBreedImage
import com.gmail.shtukarrv.dogbreedsapp.domain.usecase.DogBreedImagesUseCase
import com.gmail.shtukarrv.dogbreedsapp.presentation.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DogBreedImagesViewModel @Inject constructor(private val dogBreedImagesUseCase: DogBreedImagesUseCase) :
    ViewModel() {

    private var initialized = false

    private val dogBreedImages = MutableLiveData<Resource<List<DogBreedImage>>>()
    fun getDogBreedImages(): LiveData<Resource<List<DogBreedImage>>> = dogBreedImages

    private val dogBreedFullName = MutableLiveData<String>()
    fun getDogBreedFullName(): LiveData<String> = dogBreedFullName

    var dogBreedName = ""
    var dogSubBreedName = ""

    fun initialize(args: Bundle?) {
        if (initialized) return

        dogBreedName = args?.getString(ARG_DOG_BREED_NAME) ?: ""
        dogSubBreedName = args?.getString(ARG_DOG_SUB_BREED_NAME) ?: ""

        dogBreedFullName.postValue("$dogBreedName $dogSubBreedName".capitalize())

        initialized = true
    }

    fun fetchDogBreedImages() {
        viewModelScope.launch {
            dogBreedImages.postValue(Resource.loading(null))

            val flow: Flow<List<DogBreedImage>> = if (dogSubBreedName.isNotEmpty()) {
                dogBreedImagesUseCase.getDogSubBreedImages(dogBreedName, dogSubBreedName)
            } else {
                dogBreedImagesUseCase.getDogBreedImages(dogBreedName)
            }

            flow.catch { e ->
                dogBreedImages.postValue(Resource.error(e.toString(), null))
            }.collect {
                dogBreedImages.postValue(Resource.success(it))
            }
        }
    }

    companion object {
        const val ARG_DOG_BREED_NAME = "ARG_DOG_BREED_NAME"
        const val ARG_DOG_SUB_BREED_NAME = "ARG_DOG_SUB_BREED_NAME"
    }
}
