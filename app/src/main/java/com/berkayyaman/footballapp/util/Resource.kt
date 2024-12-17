package com.berkayyaman.footballapp.util

import retrofit2.Response

/**
 * Created by berkayyaman on 11,October,2024
 */
sealed class Resource<out T> {

    class Success<out T>(
        val result: T
    ) : Resource<T>()

    class Error(
        val message: String
    ) : Resource<Nothing>()

    data object Loading : Resource<Nothing>()
}


fun <T> Response<T>.asResource(): Resource<T> {
    try {
        if (isSuccessful) {
            return if (body() != null) {
                Resource.Success(
                    result = body()!!
                )
            } else {
                Resource.Error(
                    message = message()
                )
            }

        } else {
            return Resource.Error(
                message = message()
            )
        }
    } catch (e: Exception) {
        return Resource.Error(
            message = e.message ?: "An error Occured"
        )
    }
}

inline fun <T,R> Resource<T>.mapOnSuccess(map : (T?) -> R) : Resource<R> = when(this){
    is Resource.Success -> Resource.Success(map(result))
    is Resource.Error -> this
    is Resource.Loading -> this

}

inline fun <T> Resource<T>.onSuccess(call: (T) -> Unit): Resource<T> = when(this){
    is Resource.Success -> {
        call.invoke(result)
        this
    }
    is Resource.Error -> this
    is Resource.Loading -> this
}