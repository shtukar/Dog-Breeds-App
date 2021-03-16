package com.gmail.shtukarrv.dogbreedsapp.domain

import com.gmail.shtukarrv.dogbreedsapp.BaseUnitTest
import com.gmail.shtukarrv.dogbreedsapp.domain.repository.DogBreedsRepository
import com.gmail.shtukarrv.dogbreedsapp.domain.usecase.DogBreedsUseCaseImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class DogBreedsUseCaseTest : BaseUnitTest() {

    @Mock
    private lateinit var repository: DogBreedsRepository

    private lateinit var useCase: DogBreedsUseCaseImpl

    @Before
    fun setup() {
        useCase = DogBreedsUseCaseImpl(repository)
    }

    @Test
    fun `should call getDogBreeds from repository`() {
        testCoroutineRule.runBlockingTest {
            useCase.getDogBreeds()
            verify(repository, times(1)).getDogBreeds()
        }
    }
}