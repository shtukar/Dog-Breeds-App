package com.gmail.shtukarrv.dogbreedsapp.presentation

import androidx.lifecycle.Observer
import com.gmail.shtukarrv.dogbreedsapp.BaseUnitTest
import com.gmail.shtukarrv.dogbreedsapp.domain.entity.DogBreed
import com.gmail.shtukarrv.dogbreedsapp.domain.usecase.DogBreedsUseCase
import com.gmail.shtukarrv.dogbreedsapp.presentation.ui.dog_breeds_list.DogBreedsListViewModel
import com.gmail.shtukarrv.dogbreedsapp.presentation.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class DogBreedsListViewModelTest : BaseUnitTest() {

    @Mock
    private lateinit var dogBreedsUseCase: DogBreedsUseCase

    @Mock
    private lateinit var apiDogBreedsObserver: Observer<Resource<List<DogBreed>>>

    @Test
    fun `fetchDogBreeds successful`() {
        testCoroutineRule.runBlockingTest {
            val listResult = listOf(DogBreed("Name", listOf()))
            doReturn(flowOf(listResult))
                .`when`(dogBreedsUseCase).getDogBreeds()

            val viewModel = DogBreedsListViewModel(dogBreedsUseCase)

            viewModel.getDogBreeds().observeForever(apiDogBreedsObserver)
            verify(dogBreedsUseCase).getDogBreeds()
            verify(apiDogBreedsObserver).onChanged(Resource.success(listResult))
            viewModel.getDogBreeds().removeObserver(apiDogBreedsObserver)
        }
    }

    @Test
    fun `fetchDogBreeds empty list`() {
        testCoroutineRule.runBlockingTest {
            doReturn(flowOf(emptyList<DogBreed>()))
                .`when`(dogBreedsUseCase).getDogBreeds()

            val viewModel = DogBreedsListViewModel(dogBreedsUseCase)

            viewModel.getDogBreeds().observeForever(apiDogBreedsObserver)
            verify(dogBreedsUseCase).getDogBreeds()
            verify(apiDogBreedsObserver).onChanged(Resource.success(emptyList()))
            viewModel.getDogBreeds().removeObserver(apiDogBreedsObserver)
        }
    }

    @Test
    fun `fetchDogBreeds error`() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error Message For You"
            doReturn(flow<List<DogBreed>> { throw IllegalStateException(errorMessage) })
                .`when`(dogBreedsUseCase)
                .getDogBreeds()

            val viewModel = DogBreedsListViewModel(dogBreedsUseCase)

            viewModel.getDogBreeds().observeForever(apiDogBreedsObserver)
            verify(dogBreedsUseCase).getDogBreeds()
            verify(apiDogBreedsObserver).onChanged(
                Resource.error(
                    IllegalStateException(errorMessage).toString(),
                    null
                )
            )
            viewModel.getDogBreeds().removeObserver(apiDogBreedsObserver)
        }
    }

}