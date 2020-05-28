package com.timeline.recta.apiService.webapi

import androidx.lifecycle.MutableLiveData
import core.webapis.Error
import core.webapis.ErrorUtils
import core.webapis.ResourceError
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkCall<T> {


    lateinit var call: Call<T>

    fun makeCall(call: Call<T>): MutableLiveData<Resource<T>> {
        this.call = call
        val callBackKt = CallBackKt<T>()
        callBackKt.result.value = Resource.loading(null)
        this.call.clone().enqueue(callBackKt)
        return callBackKt.result
    }

    class CallBackKt<T> : Callback<T> {
        private val INVALID_TOKEN: Int = 401

        var result: MutableLiveData<Resource<T>> = MutableLiveData()
        override fun onFailure(call: Call<T>, t: Throwable) {
            var error = ResourceError()
            error.error = core.webapis.Error(0, t.message!!)
            result.value = Resource.error(error)
            t.printStackTrace()
            result.value = Resource.completed()
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful) {
                result.value = Resource.success(response.body())
            } else {
                val code = response.code()
                val body = response.errorBody()

                when (code) {
                    INVALID_TOKEN -> {
                        result.value =
                            Resource.invalidToken(ErrorUtils().parseTokenError(body, code))
                    }
                    else -> {
                        result.value = Resource.error(ErrorUtils().parseError(body, code))
                    }
                }

            }
            result.value = Resource.completed()
        }
    }

    fun cancel() {
        if (::call.isInitialized) {
            call.cancel()
        }
    }
}