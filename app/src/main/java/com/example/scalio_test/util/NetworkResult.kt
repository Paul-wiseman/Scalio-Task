package com.example.scalio_test.util

/**Sealed class for Handling Api Response*/
sealed class NetworkResult<T>(
    val data: T? = null,
    val errorText: String? = null
) {
    class Success<T>(data: T) : NetworkResult<T>(data)
    class Failure<T>(errorText: String?, data: T? = null) : NetworkResult<T>(data, errorText)
    class Loading<T> : NetworkResult<T>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Failure -> "Error[Error=$errorText]"
            is Loading -> "Loading----"
        }
    }
}
