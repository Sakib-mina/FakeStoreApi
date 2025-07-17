package com.example.fakestoreapi.data.services

import com.example.fakestoreapi.data.ApiResponse.ResponseProfile
import retrofit2.Response
import retrofit2.http.GET

/**
 * Retrofit service interface for user-related operations such as profile retrieval.
 *
 * This interface is typically used after user authentication to fetch user-specific data,
 * such as the logged-in user's profile.
 *
 * Base URL example: `https://api.example.com/`
 */
interface UserService {

    /**
     * Fetches the profile of the currently authenticated user.
     *
     * This is a suspending function that executes an HTTP GET request to the `auth/profile` endpoint.
     * It requires a valid access token (usually passed as a Bearer token in the `Authorization` header).
     *
     * @return A [Response] object wrapping [ResponseProfile], which contains the user's profile information.
     *
     * Example request:
     * ```
     * GET /auth/profile
     * Headers:
     *   Authorization: Bearer <access_token>
     * ```
     *
     * Example response (200 OK):
     * ```json
     * {
     *   "id": 1,
     *   "name": "John Doe",
     *   "email": "john@example.com",
     *   "role": "user"
     * }
     * ```
     *
     * Error response (401 Unauthorized):
     * ```json
     * {
     *   "message": "Unauthorized"
     * }
     * ```
     *
     * @throws HttpException if the response is not successful.
     */
    @GET("auth/profile")
    suspend fun profile(): Response<ResponseProfile>
}
