package com.berkayyaman.footballapp.domain.repository

import com.berkayyaman.footballapp.data.remote.dto.BaseEntity
import com.berkayyaman.footballapp.util.Resource
import com.berkayyaman.footballapp.util.asResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

/**
 * Created by berkayyaman on 11,October,2024
 */
open class BaseRepository {

    suspend inline fun <reified T : BaseEntity> request(
        crossinline call: suspend () -> Response<T>
    ): Resource<T> = withContext(Dispatchers.IO) {
        try {
            call.invoke().asResource()
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An Error Occurred")
        }
    }
}