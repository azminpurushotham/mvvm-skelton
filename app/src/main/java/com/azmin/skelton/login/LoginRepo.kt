package com.azmin.skelton.login

import androidx.lifecycle.LiveData
import com.azmin.skelton.login.model.LoginUserModel
import com.timeline.recta.apiService.webapi.NetworkCall
import com.timeline.recta.apiService.webapi.Resource
import core.webapis.ApiService
import core.base.BaseRepository
import core.webapis.BaseResponse
import javax.inject.Inject


class LoginRepo @Inject constructor(var api: ApiService) : BaseRepository() {

    fun login(param: LoginUserModel): LiveData<Resource<BaseResponse<Any>>> {
        return NetworkCall<BaseResponse<Any>>()
            .makeCall(
                api.createService(APILogin::class.java)
                    .login(param)
            )
    }

}