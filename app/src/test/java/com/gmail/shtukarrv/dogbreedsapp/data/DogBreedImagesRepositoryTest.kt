package com.gmail.shtukarrv.dogbreedsapp.data

import com.gmail.shtukarrv.dogbreedsapp.BaseUnitTest
import com.gmail.shtukarrv.dogbreedsapp.data.api.DogBreedsApi
import com.gmail.shtukarrv.dogbreedsapp.data.entity.DogBreedImageResponse
import com.gmail.shtukarrv.dogbreedsapp.data.repository.DogBreedImagesRepositoryImpl
import com.gmail.shtukarrv.dogbreedsapp.domain.repository.DogBreedImagesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import retrofit2.Response

@ExperimentalCoroutinesApi
class DogBreedImagesRepositoryTest : BaseUnitTest() {

    @Mock
    private lateinit var api: DogBreedsApi

    @Mock
    private lateinit var response: Response<DogBreedImageResponse>

    private lateinit var repository: DogBreedImagesRepository

    private var dogBreedName = "Name"
    private var dogBreedSubName = "SubName"

    @Before
    fun setup() {
        repository = DogBreedImagesRepositoryImpl(api)
    }

    @Test
    fun `should return list from getDogBreedImages function from repository`() {
        testCoroutineRule.runBlockingTest {
            val dogBreedImageResponse =
                DogBreedImageResponse(status = "success", listOf("imageUrl1", "imageUrl2"))

            Mockito.doReturn(response)
                .`when`(api).getDogBreedImages(dogBreedName)
            Mockito.doReturn(true)
                .`when`(response).isSuccessful
            Mockito.doReturn(dogBreedImageResponse)
                .`when`(response).body()

            Assert.assertEquals(
                repository.getDogBreedImages(dogBreedName).first(),
                dogBreedImageResponse.toDogBreedImagesList()
            )
            verify(api, times(1)).getDogBreedImages(dogBreedName)
        }
    }

    @Test
    fun `should return data error from getDogBreedImages function from repository`() {
        testCoroutineRule.runBlockingTest {
            val dogBreedImageResponse = DogBreedImageResponse(status = "failure", listOf())

            Mockito.doReturn(response)
                .`when`(api).getDogBreedImages(dogBreedName)
            Mockito.doReturn(true)
                .`when`(response).isSuccessful
            Mockito.doReturn(dogBreedImageResponse)
                .`when`(response).body()

            try {
                repository.getDogBreedImages(dogBreedName).first()
                Assert.fail("Should have thrown FetchDataException exception")
            } catch (e: FetchDataException) {
                //success
            }

            verify(api, times(1)).getDogBreedImages(dogBreedName)
        }
    }

    @Test
    fun `should return server error from getDogBreedImages function from repository`() {
        testCoroutineRule.runBlockingTest {
            Mockito.doReturn(response)
                .`when`(api).getDogBreedImages(dogBreedName)
            Mockito.doReturn(false)
                .`when`(response).isSuccessful

            try {
                repository.getDogBreedImages(dogBreedName).first()
                Assert.fail("Should have thrown ServerErrorException exception")
            } catch (e: ServerErrorException) {
                //success
            }

            verify(api, times(1)).getDogBreedImages(dogBreedName)
        }
    }

    @Test
    fun `should return list from getDogSubBreedImages function from repository`() {
        testCoroutineRule.runBlockingTest {
            val dogBreedImageResponse =
                DogBreedImageResponse(status = "success", listOf("imageUrl1", "imageUrl2"))

            Mockito.doReturn(response)
                .`when`(api).getDogSubBreedImages(dogBreedName, dogBreedSubName)
            Mockito.doReturn(true)
                .`when`(response).isSuccessful
            Mockito.doReturn(dogBreedImageResponse)
                .`when`(response).body()

            Assert.assertEquals(
                repository.getDogSubBreedImages(dogBreedName, dogBreedSubName).first(),
                dogBreedImageResponse.toDogBreedImagesList()
            )
            verify(api, times(1)).getDogSubBreedImages(dogBreedName, dogBreedSubName)
        }
    }

    @Test
    fun `should return data error from getDogSubBreedImages function from repository`() {
        testCoroutineRule.runBlockingTest {
            val dogBreedImageResponse = DogBreedImageResponse(status = "failure", listOf())

            Mockito.doReturn(response)
                .`when`(api).getDogSubBreedImages(dogBreedName, dogBreedSubName)
            Mockito.doReturn(true)
                .`when`(response).isSuccessful
            Mockito.doReturn(dogBreedImageResponse)
                .`when`(response).body()

            try {
                repository.getDogSubBreedImages(dogBreedName, dogBreedSubName).first()
                Assert.fail("Should have thrown FetchDataException exception")
            } catch (e: FetchDataException) {
                //success
            }

            verify(api, times(1)).getDogSubBreedImages(dogBreedName, dogBreedSubName)
        }
    }

    @Test
    fun `should return server error from getDogSubBreedImages function from repository`() {
        testCoroutineRule.runBlockingTest {
            Mockito.doReturn(response)
                .`when`(api).getDogSubBreedImages(dogBreedName, dogBreedSubName)
            Mockito.doReturn(false)
                .`when`(response).isSuccessful

            try {
                repository.getDogSubBreedImages(dogBreedName, dogBreedSubName).first()
                Assert.fail("Should have thrown ServerErrorException exception")
            } catch (e: ServerErrorException) {
                //success
            }

            verify(api, times(1)).getDogSubBreedImages(dogBreedName, dogBreedSubName)
        }
    }
}