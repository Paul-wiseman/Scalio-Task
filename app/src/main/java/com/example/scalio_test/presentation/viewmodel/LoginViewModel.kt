package com.example.scalio_test.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scalio_test.data.model.LoginResponse
import com.example.scalio_test.domain.usecase.Interator
import com.example.scalio_test.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val interator: Interator) :ViewModel() {
    private val _loginResponse = MutableSharedFlow<NetworkResult<LoginResponse>>()
    val loginResponse = _loginResponse.asSharedFlow()
    fun login(q:String){
        viewModelScope.launch(Dispatchers.IO) {
                _loginResponse.emit(NetworkResult.Loading())
            val response = interator.provideLoginUseCase().invoke(q)
            Log.d("LoginViewModel", "login: ${response.raw()}")
            Log.d("LoginViewModel", "login: ${response.body()}")
            if (response.isSuccessful && response.body() != null){
                _loginResponse.emit(NetworkResult.Success(response.body()!!))
            }else{
                _loginResponse.emit(NetworkResult.Failure(response.message()))
            }
        }
    }


}