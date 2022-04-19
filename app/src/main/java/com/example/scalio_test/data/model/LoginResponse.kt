package com.example.scalio_test.data.model

data class LoginResponse(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)