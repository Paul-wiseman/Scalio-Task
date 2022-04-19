package com.example.scalio_test.di

import com.example.scalio_test.constants.constants.BASE_URL
import com.example.scalio_test.data.datasource.ApiService
import com.example.scalio_test.data.datasource.InteratorsImpl
import com.example.scalio_test.data.datasource.LoginDataSource
import com.example.scalio_test.domain.repository.LoginRepository
import com.example.scalio_test.domain.usecase.Interator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideHttpClient(
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(
        okHttpClient: OkHttpClient
    ): ApiService =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).client(okHttpClient)
            .build().create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideLoginRepository(apiService: ApiService):LoginRepository =
         LoginDataSource(apiService)

    @Singleton
    @Provides
    fun provideInterators(loginRepository: LoginRepository):Interator = InteratorsImpl(loginRepository)

}