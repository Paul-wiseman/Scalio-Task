package com.example.scalio_test.data.datasource

import com.example.scalio_test.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    suspend fun login(
        @Query("q") q:String
    ):Response<LoginResponse>
}