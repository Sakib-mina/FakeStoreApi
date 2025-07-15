package com.example.fakestoreapi.data.ApiRequest

import com.google.gson.annotations.SerializedName

/**
 * Data class representing the login request body.
 *
 * This class is used to send user login credentials (email and password)
 * to the authentication API via Retrofit.
 *
 * @property email The email address of the user.
 * @property password The password of the user.
 */
data class LoginRequest(
    @SerializedName("email")
    var email: String?,

    @SerializedName("password")
    var password: String?
)