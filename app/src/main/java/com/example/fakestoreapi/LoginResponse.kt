package com.example.fakestoreapi


import com.google.gson.annotations.SerializedName

/**
 * Data class representing the response from the login API.
 *
 * This class maps the JSON response returned by the backend
 * after a successful login, including both access and refresh tokens.
 *
 * @property accessToken A short-lived token used for accessing protected resources.
 * @property refreshToken A long-lived token used to obtain a new access token when it expires.
 */
data class LoginResponse(

    @SerializedName("access_token")
    val accessToken: String?,

    @SerializedName("refresh_token")
    val refreshToken: String?
)