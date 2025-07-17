package com.example.fakestoreapi.data.repository

import com.example.fakestoreapi.data.ApiResponse.ResponseProfile
import com.example.fakestoreapi.data.services.UserService
import retrofit2.Response
import javax.inject.Inject

/**
 * Repository responsible for handling user-related data operations such as
 * retrieving user profile, authentication, and account updates.
 *
 * Acts as a single source of truth for the UI/ViewModel layer and communicates
 * with the backend API through the [UserService].
 *
 * @property service Retrofit service interface that defines user-related API calls.
 * @constructor Injects the [UserService] dependency using Hilt.
 */
class UserRepository @Inject constructor(
    private val service: UserService
) {

    /**
     * Fetches the authenticated user's profile from the backend API.
     *
     * @return A [Response] containing the [ResponseProfile] data.
     *         Returns HTTP status, body, headers, etc.
     * @throws Exception if the network request fails.
     *
     * Usage example:
     * ```
     * val response = userRepository.profile()
     * if (response.isSuccessful) {
     *     val profile = response.body()
     * }
     * ```
     */
    suspend fun profile(): Response<ResponseProfile> {
        return service.profile()
    }
}