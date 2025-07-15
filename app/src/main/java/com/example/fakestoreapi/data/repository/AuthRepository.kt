package com.example.fakestoreapi.data.repository

import com.example.fakestoreapi.data.services.AuthService
import com.example.fakestoreapi.data.ApiRequest.LoginRequest
import com.example.fakestoreapi.data.ApiRequest.RegisterRequest
import com.example.fakestoreapi.data.ApiResponse.LoginResponse
import com.example.fakestoreapi.data.ApiResponse.RegisterResponse
import retrofit2.Response
import javax.inject.Inject

/**
 * Repository class that handles authentication-related operations.
 *
 * This class abstracts the source of authentication data (network in this case)
 * and provides a clean API for the rest of the application to interact with.
 *
 * @param service An implementation of [AuthService] used to perform network calls.
 */
class AuthRepository @Inject constructor(
    private val service: AuthService
) {

    /**
     * Sends a login request to the authentication API.
     *
     * @param request A [com.example.fakestoreapi.data.ApiRequest.LoginRequest] object containing the user's login credentials.
     * @return A [retrofit2.Response] object containing either a [com.example.fakestoreapi.data.ApiResponse.LoginResponse] on success,
     *         or an error from the server on failure.
     */
    suspend fun login(request: LoginRequest): Response<LoginResponse> {
        return service.login(request)
    }

    suspend fun register(registerRequest: RegisterRequest): Response<RegisterResponse> {
        return service.register(registerRequest)
    }
}