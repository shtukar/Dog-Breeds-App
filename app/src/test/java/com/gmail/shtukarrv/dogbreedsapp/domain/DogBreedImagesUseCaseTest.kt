package com.gmail.shtukarrv.dogbreedsapp.domain

import com.gmail.shtukarrv.dogbreedsapp.BaseUnitTest
import com.gmail.shtukarrv.dogbreedsapp.domain.repository.DogBreedImagesRepository
import com.gmail.shtukarrv.dogbreedsapp.domain.usecase.DogBreedImagesUseCaseImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class DogBreedImagesUseCaseTest : BaseUnitTest() {

    @Mock
    private lateinit var repository: DogBreedImagesRepository

    private lateinit var useCase: DogBreedImagesUseCaseImpl

    private var dogBreedName = "Name"
    private var dogBreedSubName = "SubName"

    @Before
    fun setup() {
        useCase = DogBreedImagesUseCaseImpl(repository)
    }

    @Test
    fun `should call getDogBreedImages from repository`() {
        testCoroutineRule.runBlockingTest {
            useCase.getDogBreedImages(dogBreedName)

            verify(repository, times(1)).getDogBreedImages(dogBreedName)
        }
    }

    @Test
    fun `should call getDogSubBreedImages from repository`() {
        testCoroutineRule.runBlockingTest {
            useCase.getDogSubBreedImages(dogBreedName, dogBreedSubName)

            verify(repository, times(1)).getDogSubBreedImages(dogBreedName, dogBreedSubName)
        }
    }
}