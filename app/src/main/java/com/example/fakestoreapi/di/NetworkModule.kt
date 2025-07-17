package com.example.fakestoreapi.di

import com.example.fakestoreapi.data.pref.AuthInterceptor
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
 * Hilt module that provides network-related dependencies such as Retrofit, OkHttpClient,
 * and API service interfaces (e.g., AuthService, UserService, FakeStoreApi).
 *
 * All dependencies are scoped to the SingletonComponent, meaning they will live
 * as long as the application is alive (Application Scope).
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.escuelajs.co/api/v1/"

    /**
     * Provides a base Retrofit.Builder configured with the base URL and a Gson converter.
     *
     * @return A pre-configured Retrofit.Builder instance for creating Retrofit clients.
     */
    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    /**
     * Provides an OkHttpClient with an authentication interceptor for attaching auth tokens
     * or headers to every network request.
     *
     * @param authInterceptor An instance of [AuthInterceptor] to handle request authentication.
     * @return A customized [OkHttpClient] with the interceptor.
     */
    @Provides
    @Singleton
    fun provideHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    /**
     * Provides a Retrofit instance using the shared OkHttpClient and Retrofit.Builder.
     *
     * @param builder A [Retrofit.Builder] instance.
     * @param client A configured [OkHttpClient] instance.
     * @return A fully initialized [Retrofit] instance.
     */
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

    /**
     * Provides an implementation of [AuthService] using Retrofit.
     *
     * @param retrofit A [Retrofit] instance used to create the service.
     * @return An instance of [AuthService].
     */
    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    /**
     * Provides an implementation of [UserService] using Retrofit.
     *
     * @param retrofit A [Retrofit] instance used to create the service.
     * @return An instance of [UserService].
     */
    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    /**
     * Provides an implementation of [FakeStoreApi] used for fetching products and categories.
     *
     * @param retrofit A [Retrofit] instance used to create the service.
     * @return An instance of [FakeStoreApi].
     */
    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): FakeStoreApi =
        retrofit.create(FakeStoreApi::class.java)
}
