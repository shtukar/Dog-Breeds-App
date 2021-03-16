package com.gmail.shtukarrv.dogbreedsapp.di.modules

import android.content.Context
import androidx.room.Room
import com.gmail.shtukarrv.dogbreedsapp.data.local.AppDatabase
import com.gmail.shtukarrv.dogbreedsapp.data.local.DogBreedsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistenceModule {

    @Singleton
    @Provides
    fun provideAppDb(context: Context): AppDatabase = Room
        .databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDogDao(db: AppDatabase): DogBreedsDao = db.getDogBreedsDao()
}