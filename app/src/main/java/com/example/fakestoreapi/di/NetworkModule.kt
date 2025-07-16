package com.example.fakestoreapi.di

import com.example.fakestoreapi.utils.AuthInterceptor
import com.example.fakestoreapi.data.services.FakeStoreApi
import com.example.fakestoreapi.data.services.UserService
import com.example.fakestoreapi.data.services.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
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

    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    @Singleton
    fun provideHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        builder: Retrofit.Builder,
        client: OkHttpClient
    ): Retrofit {
        return builder
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): FakeStoreApi =
        retrofit.create(FakeStoreApi::class.java)
}
