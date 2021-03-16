package com.gmail.shtukarrv.dogbreedsapp.presentation.ui.dog_breeds_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.shtukarrv.dogbreedsapp.domain.entity.DogBreed
import com.gmail.shtukarrv.dogbreedsapp.domain.usecase.DogBreedsUseCase
import com.gmail.shtukarrv.dogbreedsapp.presentation.utils.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DogBreedsListViewModel @Inject constructor(private val dogBreedsUseCase: DogBreedsUseCase) :
    ViewModel() {

    private val dogBreeds = MutableLiveData<Resource<List<DogBreed>>>()

    fun getDogBreeds(): LiveData<Resource<List<DogBreed>>> = dogBreeds

    init {
        fetchDogBreeds()
    }

    private fun fetchDogBreeds() {
        viewModelScope.launch {
            dogBreeds.postValue(Resource.loading(null))
            dogBreedsUseCase.getDogBreeds()
                .catch { e ->
                    dogBreeds.postValue(Resource.error(e.toString(), null))
                }
                .collect {
                    dogBreeds.postValue(Resource.success(it))
                }
        }
    }
}
