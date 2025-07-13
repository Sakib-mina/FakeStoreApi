package com.example.fakestoreapi

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
     * @param request A [LoginRequest] object containing the user's login credentials.
     * @return A [Response] object containing either a [LoginResponse] on success,
     *         or an error from the server on failure.
     */
    suspend fun login(request: LoginRequest): Response<LoginResponse> {
        return service.login(request)
    }
}
