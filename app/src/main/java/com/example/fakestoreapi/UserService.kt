package com.example.fakestoreapi

import retrofit2.Response
import retrofit2.http.GET

interface UserService {

    @GET("auth/profile")
    suspend fun profile(): Response<ResponseProfile>

}