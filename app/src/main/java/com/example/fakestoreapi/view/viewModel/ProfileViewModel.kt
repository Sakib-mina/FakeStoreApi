package com.example.fakestoreapi.view.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestoreapi.data.ApiResponse.ResponseProfile
import com.example.fakestoreapi.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

/**
 * ViewModel responsible for fetching and holding user profile data.
 *
 * Uses Hilt for dependency injection of UserRepository,
 * and exposes profile data as LiveData for UI observation.
 *
 * @property repository Injected instance of UserRepository to fetch profile data.
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    // Backing MutableLiveData for the API response, encapsulated from external modification
    private val _response = MutableLiveData<Response<ResponseProfile>>()

    /**
     * LiveData exposed to UI layers to observe profile response updates.
     */
    val profileResponse: LiveData<Response<ResponseProfile>> = _response

    /**
     * Initialization block to fetch profile data immediately when ViewModel is created.
     */
    init {
        fetchProfile()
    }

    /**
     * Launches a coroutine in ViewModel scope to asynchronously fetch
     * user profile data from repository and post the result to LiveData.
     *
     * This approach avoids blocking the UI thread.
     */
    private fun fetchProfile() {
        viewModelScope.launch {
            val result = repository.profile()
            _response.postValue(result)
        }
    }
}
