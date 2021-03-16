package com.gmail.shtukarrv.dogbreedsapp.presentation

import androidx.lifecycle.Observer
import com.gmail.shtukarrv.dogbreedsapp.BaseUnitTest
import com.gmail.shtukarrv.dogbreedsapp.domain.entity.DogBreedImage
import com.gmail.shtukarrv.dogbreedsapp.domain.usecase.DogBreedImagesUseCase
import com.gmail.shtukarrv.dogbreedsapp.presentation.ui.dog_breed_images.DogBreedImagesViewModel
import com.gmail.shtukarrv.dogbreedsapp.presentation.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class DogBreedImagesViewModelTest : BaseUnitTest() {

    @Mock
    private lateinit var dogBreedImagesUseCase: DogBreedImagesUseCase

    @Mock
    private lateinit var apiDogBreedImagesObserver: Observer<Resource<List<DogBreedImage>>>

    private lateinit var viewModel: DogBreedImagesViewModel

    private var dogBreedName = "Name"
    private var dogBreedSubName = "SubName"

    @Before
    fun setup() {
        viewModel = DogBreedImagesViewModel(dogBreedImagesUseCase)
    }

    @Test
    fun `fetchDogBreedImages successful`() {
        testCoroutineRule.runBlockingTest {
            val listResult = listOf(DogBreedImage(dogBreedName))
            doReturn(flowOf(listResult))
                .`when`(dogBreedImagesUseCase).getDogBreedImages(dogBreedName)

            viewModel.dogBreedName = dogBreedName
            viewModel.fetchDogBreedImages()

            viewModel.getDogBreedImages().observeForever(apiDogBreedImagesObserver)
            verify(dogBreedImagesUseCase).getDogBreedImages(dogBreedName)
            verify(apiDogBreedImagesObserver).onChanged(Resource.success(listResult))
            viewModel.getDogBreedImages().removeObserver(apiDogBreedImagesObserver)
        }
    }

    @Test
    fun `fetchDogBreedImages empty list`() {
        testCoroutineRule.runBlockingTest {
            doReturn(flowOf(emptyList<DogBreedImage>()))
                .`when`(dogBreedImagesUseCase).getDogBreedImages(dogBreedName)

            viewModel.dogBreedName = dogBreedName
            viewModel.fetchDogBreedImages()

            viewModel.getDogBreedImages().observeForever(apiDogBreedImagesObserver)
            verify(dogBreedImagesUseCase).getDogBreedImages(dogBreedName)
            verify(apiDogBreedImagesObserver).onChanged(Resource.success(emptyList()))
            viewModel.getDogBreedImages().removeObserver(apiDogBreedImagesObserver)
        }
    }

    @Test
    fun `fetchDogBreeds error`() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error Message For You"
            doReturn(flow<List<DogBreedImage>> { throw IllegalStateException(errorMessage) })
                .`when`(dogBreedImagesUseCase)
                .getDogBreedImages(dogBreedName)

            viewModel.dogBreedName = dogBreedName
            viewModel.fetchDogBreedImages()

            viewModel.getDogBreedImages().observeForever(apiDogBreedImagesObserver)
            verify(dogBreedImagesUseCase).getDogBreedImages(dogBreedName)
            verify(apiDogBreedImagesObserver).onChanged(
                Resource.error(
                    IllegalStateException(errorMessage).toString(),
                    null
                )
            )
            viewModel.getDogBreedImages().removeObserver(apiDogBreedImagesObserver)
        }
    }

    @Test
    fun `fetchDogBreedImages with sub-name successful`() {
        testCoroutineRule.runBlockingTest {
            val listResult = listOf(DogBreedImage(dogBreedName))
            doReturn(flowOf(listResult))
                .`when`(dogBreedImagesUseCase).getDogSubBreedImages(dogBreedName, dogBreedSubName)

            viewModel.dogBreedName = dogBreedName
            viewModel.dogSubBreedName = dogBreedSubName
            viewModel.fetchDogBreedImages()

            viewModel.getDogBreedImages().observeForever(apiDogBreedImagesObserver)
            verify(dogBreedImagesUseCase).getDogSubBreedImages(dogBreedName, dogBreedSubName)
            verify(apiDogBreedImagesObserver).onChanged(Resource.success(listResult))
            viewModel.getDogBreedImages().removeObserver(apiDogBreedImagesObserver)
        }
    }

    @Test
    fun `fetchDogBreedImages with sub-name empty list`() {
        testCoroutineRule.runBlockingTest {
            doReturn(flowOf(emptyList<DogBreedImage>()))
                .`when`(dogBreedImagesUseCase).getDogSubBreedImages(dogBreedName, dogBreedSubName)

            viewModel.dogBreedName = dogBreedName
            viewModel.dogSubBreedName = dogBreedSubName
            viewModel.fetchDogBreedImages()

            viewModel.getDogBreedImages().observeForever(apiDogBreedImagesObserver)
            verify(dogBreedImagesUseCase).getDogSubBreedImages(dogBreedName, dogBreedSubName)
            verify(apiDogBreedImagesObserver).onChanged(Resource.success(emptyList()))
            viewModel.getDogBreedImages().removeObserver(apiDogBreedImagesObserver)
        }
    }

    @Test
    fun `fetchDogBreeds with sub-name error`() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error Message For You"
            doReturn(flow<List<DogBreedImage>> { throw IllegalStateException(errorMessage) })
                .`when`(dogBreedImagesUseCase)
                .getDogSubBreedImages(dogBreedName, dogBreedSubName)

            viewModel.dogBreedName = dogBreedName
            viewModel.dogSubBreedName = dogBreedSubName
            viewModel.fetchDogBreedImages()

            viewModel.getDogBreedImages().observeForever(apiDogBreedImagesObserver)
            verify(dogBreedImagesUseCase).getDogSubBreedImages(dogBreedName, dogBreedSubName)
            verify(apiDogBreedImagesObserver).onChanged(
                Resource.error(
                    IllegalStateException(errorMessage).toString(),
                    null
                )
            )
            viewModel.getDogBreedImages().removeObserver(apiDogBreedImagesObserver)
        }
    }
}