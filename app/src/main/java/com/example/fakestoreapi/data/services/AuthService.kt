package com.example.fakestoreapi.data.services

import com.example.fakestoreapi.data.ApiRequest.LoginRequest
import com.example.fakestoreapi.data.ApiRequest.RegisterRequest
import com.example.fakestoreapi.data.ApiResponse.LoginResponse
import com.example.fakestoreapi.data.ApiResponse.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Retrofit interface for authentication-related network operations.
 *
 * Defines the API endpoints used for user authentication, such as login.
 * Retrofit will use this interface to generate the implementation for HTTP calls.
 */
interface AuthService {

    /**
     * Sends a POST request to the "auth/login" endpoint for user login.
     *
     * @param request A [com.example.fakestoreapi.data.ApiRequest.LoginRequest] object that includes the user's email and password.
     * @return A [retrofit2.Response] object containing either a successful [com.example.fakestoreapi.data.ApiResponse.LoginResponse]
     *         or an error from the server.
     */
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("users")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>
}