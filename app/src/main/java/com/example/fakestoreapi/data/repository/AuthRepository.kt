package com.example.fakestoreapi.data.repository

import com.example.fakestoreapi.data.services.AuthService
import com.example.fakestoreapi.data.ApiRequest.LoginRequest
import com.example.fakestoreapi.data.ApiRequest.RegisterRequest
import com.example.fakestoreapi.data.ApiResponse.LoginResponse
import com.example.fakestoreapi.data.ApiResponse.RegisterResponse
import retrofit2.Response
import javax.inject.Inject

/**
 * Repository class responsible for handling authentication-related operations,
 * such as user login and registration.
 *
 * It acts as an abstraction layer between the data source (remote API) and
 * the domain/business logic, providing a clean and testable API for ViewModels or UseCases.
 *
 * This class uses [AuthService], a Retrofit-based interface, to perform actual
 * network operations.
 *
 * @constructor Injects [AuthService] using Hilt for dependency injection.
 *
 * @param service An instance of [AuthService] used to communicate with the authentication API.
 *
 * @see AuthService
 */
class AuthRepository @Inject constructor(
    private val service: AuthService
) {

    /**
     * Sends a login request to the backend server with the user's credentials.
     *
     * @param request A [LoginRequest] object containing the user's email and password.
     * @return A [Response] containing a [LoginResponse] object if successful,
     *         or an HTTP error code/message if failed.
     *
     * Example usage:
     * ```
     * val response = authRepository.login(LoginRequest("user@example.com", "password"))
     * if (response.isSuccessful) {
     *     val loginData = response.body()
     * }
     * ```
     */
    suspend fun login(request: LoginRequest): Response<LoginResponse> {
        return service.login(request)
    }

    /**
     * Sends a registration request to the server to create a new user account.
     *
     * @param registerRequest A [RegisterRequest] containing the new user's details.
     * @return A [Response] containing a [RegisterResponse] object if registration is successful,
     *         or an error response from the server if it fails.
     *
     * Example usage:
     * ```
     * val response = authRepository.register(RegisterRequest("John", "john@example.com", "pass123"))
     * if (response.isSuccessful) {
     *     val userData = response.body()
     * }
     * ```
     */
    suspend fun register(registerRequest: RegisterRequest): Response<RegisterResponse> {
        return service.register(registerRequest)
    }
}