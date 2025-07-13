package com.example.fakestoreapi

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Hilt module that provides network-related dependencies such as Retrofit and API services.
 *
 * Installed in the SingletonComponent, meaning all provided dependencies will live
 * as long as the application does.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.escuelajs.co/api/v1/"

    /**
     * Provides a singleton instance of Retrofit.Builder with the base URL and Gson converter.
     */
    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    /**
     * Provides a singleton instance of AuthService created from Retrofit.
     *
     * @param retrofitBuilder The Retrofit builder used to build the Retrofit instance.
     */
    @Provides
    @Singleton
    fun provideAuthService(retrofitBuilder: Retrofit.Builder): AuthService {
        return retrofitBuilder
            .build()
            .create(AuthService::class.java)
    }
}
