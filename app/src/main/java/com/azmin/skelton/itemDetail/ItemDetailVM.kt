package com.azmin.skelton.itemDetail

import androidx.lifecycle.MutableLiveData
import com.azmin.skelton.AppApplication
import com.azmin.skelton.utils.SharedPreferenceUtils
import com.timeline.recta.apiService.webapi.Resource
import core.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

class ItemDetailVM @Inject constructor(
    val application: AppApplication,
    val pref: SharedPreferenceUtils,
    val repo: ItemDetailRepo
) : BaseViewModel() {

    var _list = MutableLiveData<List<String>>()
    val list get() = _list

    init {
        loadData()
    }

    override fun loadData() {
        _list.value = listOf("one", "two", "three")
        repo.loadData()
            .observeForever { response ->
                when (response?.status) {
                    Resource.Status.LOADING -> {
                        Timber.d("Loading")
                        showLoading(true)
                    }

                    Resource.Status.SUCCESS -> {
                        Timber.d("SUCCESS")
                        showLoading(false)
                    }

                    Resource.Status.ERROR -> {
                        Timber.d("ERROR")
                        Timber.v("Error " + response.resourceError)
                        showLoading(false)
                    }

                    Resource.Status.COMPLETED -> {
                        Timber.d("COMPLETED")
                        showLoading(false)
                    }
                }
            }
    }

    override fun setLoadMore() {

    }

}