package com.example.fakestoreapi.data.services

import com.example.fakestoreapi.data.ApiResponse.ResponseProfile
import retrofit2.Response
import retrofit2.http.GET

interface UserService {

    @GET("auth/profile")
    suspend fun profile(): Response<ResponseProfile>

}