package com.gmail.shtukarrv.dogbreedsapp.di.modules

import com.gmail.shtukarrv.dogbreedsapp.data.ApiConstants.BASE_URL
import com.gmail.shtukarrv.dogbreedsapp.data.ApiConstants.CONNECT_TIMEOUT
import com.gmail.shtukarrv.dogbreedsapp.data.ApiConstants.READ_TIMEOUT
import com.gmail.shtukarrv.dogbreedsapp.BuildConfig
import com.gmail.shtukarrv.dogbreedsapp.data.api.DogBreedsApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitClient(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    fun providesApi(retrofit: Retrofit): DogBreedsApi = retrofit.create(DogBreedsApi::class.java)

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .followRedirects(true)
            .followSslRedirects(true)

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }
        return builder.build()
    }
}
