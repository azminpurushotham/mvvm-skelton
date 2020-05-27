package com.azmin.skelton.home

import androidx.lifecycle.LiveData
import com.azmin.skelton.login.model.LoginUserModel
import com.timeline.recta.apiService.webapi.NetworkCall
import com.timeline.recta.apiService.webapi.Resource
import core.webapis.ApiService
import core.base.BaseRepository
import core.webapis.BaseResponse
import javax.inject.Inject


class HomeRepo @Inject constructor(var api: ApiService) : BaseRepository() {

    fun loadData(param: LoginUserModel): LiveData<Resource<BaseResponse<Any>>> {
        return NetworkCall<BaseResponse<Any>>()
            .makeCall(
                api.createService(APIHome::class.java)
                    .login(param)
            )
    }

}