package com.gmail.shtukarrv.dogbreedsapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gmail.shtukarrv.dogbreedsapp.domain.entity.DogBreed
import kotlinx.coroutines.flow.Flow

@Dao
interface DogBreedsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(dog: DogBreed)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveDogBreedsList(dogBreedsList: List<DogBreed>)

    @Query("SELECT * FROM dogBreed")
    fun getAllDogBreedsFlow(): Flow<List<DogBreed>>

    @Query("DELETE FROM dogBreed ")
    suspend fun deleteCache()

}