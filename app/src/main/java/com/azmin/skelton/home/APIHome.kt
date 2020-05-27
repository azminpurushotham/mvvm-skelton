package com.azmin.skelton.home

import com.azmin.skelton.login.model.LoginUserModel
import core.webapis.BaseResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIHome {

    @GET("sample")
    fun loadData(): Call<BaseResponse<List<Any>>>

}