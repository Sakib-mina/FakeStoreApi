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
 * This interface defines HTTP endpoints for user login and registration,
 * allowing Retrofit to generate network request implementations at runtime.
 *
 * Endpoints include:
 * - User login (`auth/login`)
 * - User registration (`users`)
 *
 * Usage Example:
 * ```
 * val response = authService.login(LoginRequest(email, password))
 * if (response.isSuccessful) {
 *     val token = response.body()?.accessToken
 * }
 * ```
 */
interface AuthService {

    /**
     * Sends a POST request to authenticate the user via the "auth/login" endpoint.
     *
     * @param request A [LoginRequest] object containing user credentials (email, password).
     * @return A [Response] containing a [LoginResponse] on success, or an error response from the server.
     */
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    /**
     * Sends a POST request to create a new user account via the "users" endpoint.
     *
     * @param registerRequest A [RegisterRequest] object containing required registration fields (e.g., name, email, password).
     * @return A [Response] containing a [RegisterResponse] on successful registration, or error details.
     */
    @POST("users")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>
}