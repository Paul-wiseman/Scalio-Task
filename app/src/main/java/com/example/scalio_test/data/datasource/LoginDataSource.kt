package com.example.scalio_test.data.datasource

import com.example.scalio_test.domain.repository.LoginRepository
import javax.inject.Inject

class LoginDataSource @Inject constructor(private val apiService: ApiService):LoginRepository {
    override suspend fun login(q: String) = apiService.login(q)
}