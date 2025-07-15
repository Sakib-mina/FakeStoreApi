package com.example.fakestoreapi.data.ApiRequest


import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("avatar")
    var avatar: String?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("password")
    var password: String?
)