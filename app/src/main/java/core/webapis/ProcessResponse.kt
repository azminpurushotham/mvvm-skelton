package com.timeline.recta.apiService.webapi

interface ProcessResponse<T> {
    fun processResponse(response: Resource<T>?)
}