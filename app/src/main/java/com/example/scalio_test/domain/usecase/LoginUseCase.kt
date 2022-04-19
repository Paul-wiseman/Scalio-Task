package com.example.scalio_test.domain.usecase

import com.example.scalio_test.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {
    suspend operator fun invoke(q:String) = loginRepository.login(q)
}