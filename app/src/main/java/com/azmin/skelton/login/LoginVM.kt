package com.azmin.skelton.login

import androidx.lifecycle.LifecycleObserver
import com.azmin.skelton.AppApplication
import com.azmin.skelton.R
import com.azmin.skelton.login.model.LoginUserModel
import com.azmin.skelton.utils.SharedPreferenceUtils
import com.timeline.recta.apiService.webapi.Resource
import core.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

class LoginVM @Inject constructor(
    val application: AppApplication,
    val pref: SharedPreferenceUtils,
    val repo: LoginRepo
) : BaseViewModel(), LifecycleObserver {

    init {
        pref.clearFireBaseToken()
        pref.clearUser()
        loadData()
    }

    override fun loadData() {
        setSuccessToast(application.getString(R.string.app_name))

    }

    override fun setLoadMore() {
    }

    fun requestLogin() {
        repo.login(LoginUserModel("", ""))
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


}