package com.example.scalio_test.domain.repository

import com.example.scalio_test.data.model.LoginResponse
import retrofit2.Response

interface LoginRepository {
    suspend fun login(q:String): Response<LoginResponse>
}