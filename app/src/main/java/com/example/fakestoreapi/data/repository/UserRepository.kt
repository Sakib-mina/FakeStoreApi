package com.example.fakestoreapi.data.repository

import com.example.fakestoreapi.data.ApiResponse.ResponseProfile
import com.example.fakestoreapi.data.services.UserService
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val service: UserService){
    suspend  fun profile (): Response<ResponseProfile> {
        return service.profile()
    }
}