package com.example.templatesampleapp.helper

sealed class ResponseState<T>(
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T) : ResponseState<T>(data)

    class Error<T>(message: String?, data: T? = null) : ResponseState<T>(data, message)

    class Loading<T> : ResponseState<T>()

    class Idle<T>(message: String) : ResponseState<T>(null, message)

}