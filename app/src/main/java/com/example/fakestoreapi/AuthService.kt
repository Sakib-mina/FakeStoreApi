package com.example.fakestoreapi

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
     * @param request A [LoginRequest] object that includes the user's email and password.
     * @return A [Response] object containing either a successful [LoginResponse]
     *         or an error from the server.
     */
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}