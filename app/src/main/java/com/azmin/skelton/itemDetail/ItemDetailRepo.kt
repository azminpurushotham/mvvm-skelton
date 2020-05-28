package com.azmin.skelton.itemDetail

import androidx.lifecycle.LiveData
import com.timeline.recta.apiService.webapi.NetworkCall
import com.timeline.recta.apiService.webapi.Resource
import core.base.BaseRepository
import core.webapis.ApiService
import core.webapis.BaseResponse
import javax.inject.Inject


class ItemDetailRepo @Inject constructor(var api: ApiService) : BaseRepository() {

    fun loadData(): LiveData<Resource<BaseResponse<List<Any>>>> {
        return NetworkCall<BaseResponse<List<Any>>>()
            .makeCall(
                api.createService(APIItemDetail::class.java)
                    .loadData()
            )
    }

}