package com.example.fakestoreapi.view.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestoreapi.data.ApiRequest.LoginRequest
import com.example.fakestoreapi.data.ApiRequest.RegisterRequest
import com.example.fakestoreapi.data.ApiResponse.LoginResponse
import com.example.fakestoreapi.data.ApiResponse.RegisterResponse
import com.example.fakestoreapi.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

/**
 * ViewModel responsible for user authentication logic.
 *
 * Manages login and registration operations by communicating with [AuthRepository].
 * Exposes LiveData for login and registration results to be observed by the UI.
 *
 * @param repository Repository handling authentication-related API calls.
 */
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    // MutableLiveData backing property for login response
    private val _loginResult = MutableLiveData<Response<LoginResponse>>()

    /**
     * LiveData exposing the login response result.
     * Observed by UI to update login status.
     */
    val loginResult: LiveData<Response<LoginResponse>> = _loginResult

    // MutableLiveData backing property for registration response
    private val _registerResult = MutableLiveData<Response<RegisterResponse>>()

    /**
     * LiveData exposing the registration response result.
     * Observed by UI to update registration status.
     */
    val registerResult: LiveData<Response<RegisterResponse>> = _registerResult

    /**
     * Performs user login with given credentials asynchronously.
     *
     * Launches a coroutine in [viewModelScope] to call repository login function.
     * Posts the response or error to [_loginResult] LiveData.
     *
     * @param email User email address.
     * @param password User password.
     */
    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val request = LoginRequest(email = email, password = password)
                val response = repository.login(request)
                _loginResult.postValue(response)
            } catch (e: Exception) {
                // Optionally log the error here
                _loginResult.postValue(
                    Response.error(
                        500,
                        "Login failed".toResponseBody()
                    )
                )
            }
        }
    }

    /**
     * Performs user registration asynchronously.
     *
     * Launches a coroutine in [viewModelScope] to call repository register function.
     * Posts the response or error to [_registerResult] LiveData.
     *
     * @param name User full name.
     * @param email User email address.
     * @param password User password.
     * @param avatar URL or base64 string for user avatar image.
     */
    fun register(name: String, email: String, password: String, avatar: String) {
        viewModelScope.launch {
            try {
                val request = RegisterRequest(
                    name = name,
                    email = email,
                    password = password,
                    avatar = avatar
                )
                val response = repository.register(request)
                _registerResult.postValue(response)
            } catch (e: Exception) {
                // Optionally log the error here
                _registerResult.postValue(
                    Response.error(
                        500,
                        "Register failed".toResponseBody()
                    )
                )
            }
        }
    }
}
