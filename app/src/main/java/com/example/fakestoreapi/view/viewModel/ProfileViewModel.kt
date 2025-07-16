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

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    private var _response = MutableLiveData<Response<ResponseProfile>>()
    val profileResponse: LiveData<Response<ResponseProfile>> = _response

    init {
        profile()
    }

    private  fun profile(){
        viewModelScope.launch {
            _response.postValue(repository.profile())
        }
    }
}