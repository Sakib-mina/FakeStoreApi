package com.example.fakestoreapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
 * It communicates with the [AuthRepository] to perform login operations and exposes results
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
}