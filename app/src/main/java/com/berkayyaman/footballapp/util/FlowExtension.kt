package com.berkayyaman.footballapp.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

/**
 * Created by berkayyaman on 11,October,2024
 */

object FlowExtensions {
    fun <T> Flow<Resource<T>>.onSuccess(action: suspend (T) -> Unit): Flow<Resource<T>> {
        return transform { restResult ->
            if (restResult is Resource.Success) {
                action.invoke(restResult.result)
            }

            emit(restResult)
        }
    }

    fun <T> Flow<Resource<T>>.onError(action: suspend (String) -> Unit): Flow<Resource<T>> {
        return transform { restResult ->
            if (restResult is Resource.Error) {
                action.invoke(restResult.message)
            }
            emit(restResult)
        }
    }
}