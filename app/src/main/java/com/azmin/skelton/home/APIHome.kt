package com.azmin.skelton.home

import com.azmin.skelton.login.model.LoginUserModel
import core.webapis.BaseResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIHome {

    @POST("SignIn")
    fun login(@Body loginUserModel: LoginUserModel):Call<BaseResponse<Any>>

}