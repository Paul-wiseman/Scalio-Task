package com.example.scalio_test.presentation.mapper

interface Mapper<T,E> {

    fun from(e: E): T

    fun to(t: T): E
}