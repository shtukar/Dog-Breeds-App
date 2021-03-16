package com.gmail.shtukarrv.dogbreedsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gmail.shtukarrv.dogbreedsapp.domain.entity.DogBreed

@TypeConverters(DataConverter::class)
@Database(entities = [DogBreed::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME: String = "app_db"
    }

    abstract fun getDogBreedsDao(): DogBreedsDao
}