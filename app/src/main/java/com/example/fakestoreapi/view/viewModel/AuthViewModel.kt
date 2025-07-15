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
 * ViewModel for handling user authentication logic.
 *
 * This class is responsible for preparing and managing the data for the UI (Login screen).
 * It communicates with the [com.example.fakestoreapi.data.repository.AuthRepository] to perform login operations and exposes results
 * to the UI via LiveData.
 *
 * @param repository The repository that handles authentication-related API calls.
 */
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    // Backing property for login result LiveData
    private val _loginResult = MutableLiveData<Response<LoginResponse>>()

    /**
     * Exposes login response to observers (UI).
     */
    val loginResult: LiveData<Response<LoginResponse>> = _loginResult

    private val _registerResult = MutableLiveData<Response<RegisterResponse>>()
    val registerResult: LiveData<Response<RegisterResponse>> = _registerResult

    /**
     * Initiates login process with provided credentials.
     *
     * @param email The user's email.
     * @param password The user's password.
     */
    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val request = LoginRequest(email = email, password = password)
                val response = repository.login(request)
                _loginResult.postValue(response)
            } catch (e: Exception) {
                // Handle error gracefully (optional: log the error or show message)
                _loginResult.postValue(
                    Response.error(
                        500,
                        "Login failed".toResponseBody()
                    )
                )
            }
        }
    }

    fun register(name: String, email: String, password: String, avatar: String) {
        viewModelScope.launch {
            try {
                val request = RegisterRequest(avatar = avatar,name = name, password = password, email = email)
                val response = repository.register(request)
                _registerResult.postValue(response)
            } catch (e: kotlin.Exception){
                _registerResult.postValue(Response.error(500,"Register failed".toResponseBody()))
            }
        }
    }
}