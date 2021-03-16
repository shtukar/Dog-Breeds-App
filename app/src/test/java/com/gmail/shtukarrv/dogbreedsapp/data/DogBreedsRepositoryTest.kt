package com.gmail.shtukarrv.dogbreedsapp.data

import com.gmail.shtukarrv.dogbreedsapp.BaseUnitTest
import com.gmail.shtukarrv.dogbreedsapp.data.api.DogBreedsApi
import com.gmail.shtukarrv.dogbreedsapp.data.entity.DogBreedResponse
import com.gmail.shtukarrv.dogbreedsapp.data.local.DogBreedsDao
import com.gmail.shtukarrv.dogbreedsapp.data.repository.DogBreedsRepositoryImpl
import com.gmail.shtukarrv.dogbreedsapp.domain.entity.DogBreed
import com.gmail.shtukarrv.dogbreedsapp.domain.repository.DogBreedsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import retrofit2.Response

@ExperimentalCoroutinesApi
class DogBreedsRepositoryTest : BaseUnitTest() {

    @Mock
    private lateinit var api: DogBreedsApi

    @Mock
    private lateinit var database: DogBreedsDao

    @Mock
    private lateinit var response: Response<DogBreedResponse>

    private lateinit var repository: DogBreedsRepository

    @Before
    fun setup() {
        repository = DogBreedsRepositoryImpl(api, database)
    }

    @Test
    fun `should return list from getDogBreeds function from repository`() {
        testCoroutineRule.runBlockingTest {
            val dogBreedResponse = DogBreedResponse(
                status = "success",
                message = mapOf("Name1" to listOf("SubName1"), "Name2" to listOf("SubName2"))
            )
            val flow = flowOf(dogBreedResponse.toDogBreedList())
            Mockito.doReturn(response)
                .`when`(api).getDogBreeds()
            Mockito.doReturn(true)
                .`when`(response).isSuccessful
            Mockito.doReturn(dogBreedResponse)
                .`when`(response).body()

            Mockito.doReturn(flow)
                .`when`(database).getAllDogBreedsFlow()

            val result = repository.getDogBreeds()

            Assert.assertEquals(flow, result)
            verify(api, times(1)).getDogBreeds()
            verify(database, times(1)).getAllDogBreedsFlow()
            verify(database, times(1)).saveDogBreedsList(dogBreedResponse.toDogBreedList())
        }
    }

    @Test
    fun `should return data error from getDogBreeds function from repository`() {
        testCoroutineRule.runBlockingTest {
            val dogBreedResponse = DogBreedResponse(
                status = "failure",
                message = mapOf()
            )
            val flow = flowOf(dogBreedResponse.toDogBreedList())
            Mockito.doReturn(response)
                .`when`(api).getDogBreeds()
            Mockito.doReturn(true)
                .`when`(response).isSuccessful
            Mockito.doReturn(dogBreedResponse)
                .`when`(response).body()

            Mockito.doReturn(flow)
                .`when`(database).getAllDogBreedsFlow()

            try {
                repository.getDogBreeds()
                Assert.fail("Should have thrown FetchDataException exception")
            } catch (e: FetchDataException) {
                //success
            }
            verify(api, times(1)).getDogBreeds()
            verify(database, times(1)).getAllDogBreedsFlow()
            verify(database, times(0)).saveDogBreedsList(dogBreedResponse.toDogBreedList())
        }
    }

    @Test
    fun `should return server error from getDogBreeds function from repository`() {
        testCoroutineRule.runBlockingTest {
            val flow = flowOf(emptyList<DogBreed>())
            Mockito.doReturn(response)
                .`when`(api).getDogBreeds()
            Mockito.doReturn(false)
                .`when`(response).isSuccessful

            Mockito.doReturn(flow)
                .`when`(database).getAllDogBreedsFlow()

            try {
                repository.getDogBreeds()
                Assert.fail("Should have thrown ServerErrorException exception")
            } catch (e: ServerErrorException) {
                //success
            }

            verify(api, times(1)).getDogBreeds()
            verify(database, times(1)).getAllDogBreedsFlow()
        }
    }
}