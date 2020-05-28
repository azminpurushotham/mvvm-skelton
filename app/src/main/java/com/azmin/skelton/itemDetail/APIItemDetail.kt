package com.azmin.skelton.itemDetail

import core.webapis.BaseResponse
import retrofit2.Call
import retrofit2.http.GET

interface APIItemDetail {

    @GET("sample")
    fun loadData(): Call<BaseResponse<List<Any>>>

}