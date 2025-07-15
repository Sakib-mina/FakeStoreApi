package com.example.fakestoreapi

import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val service: UserService){
    suspend  fun profile (): Response<ResponseProfile> {
        return service.profile()
    }
}