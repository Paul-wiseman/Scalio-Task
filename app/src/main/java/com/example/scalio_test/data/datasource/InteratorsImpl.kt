package com.example.scalio_test.data.datasource

import com.example.scalio_test.domain.repository.LoginRepository
import com.example.scalio_test.domain.usecase.Interator
import com.example.scalio_test.domain.usecase.LoginUseCase
import javax.inject.Inject

class InteratorsImpl @Inject constructor(private val loginRepository: LoginRepository):Interator {
    override fun provideLoginUseCase(): LoginUseCase =
        LoginUseCase(loginRepository)

}