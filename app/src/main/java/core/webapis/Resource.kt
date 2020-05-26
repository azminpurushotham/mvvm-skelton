package com.timeline.recta.apiService.webapi

import core.webapis.ResourceError

class Resource<T> private constructor(
    val status: Status,
    val data: T?,
    val resourceError: ResourceError?
) {
    enum class Status {
        SUCCESS, ERROR, INVALID_TOKEN, LOADING, COMPLETED
    }

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(resourceError: ResourceError?): Resource<T> {
            return Resource(Status.ERROR, null, resourceError)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

        fun <T> invalidToken(resourceError: ResourceError?): Resource<T> {
            return Resource(Status.INVALID_TOKEN, null, resourceError)
        }

        fun <T> completed(): Resource<T> {
            return Resource(Status.COMPLETED, null, null)
        }
    }
}