package core.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import core.utility.ToastState
import core.utility.ToastStateEnum
import timber.log.Timber

abstract class BaseViewModel/*(application: Application) */ : /*AndroidViewModel(application)*/
    ViewModel() {

    abstract fun loadData()
    abstract fun setLoadMore()

    var toastState = MutableLiveData<ToastState>()

    fun setInfoToast(messageResource: String) {
        toastState.value = ToastState(ToastStateEnum.INFO, messageResource)
    }

    fun setSuccessToast(messageResource: String) {
        toastState.value = ToastState(ToastStateEnum.SUCCESS, messageResource)
    }

    fun setFailureToast(messageResource: String) {
        toastState.value = ToastState(ToastStateEnum.Failure, messageResource)
    }

    var showLoading = MutableLiveData<Boolean>()
    fun showLoading(isLoading: Boolean) {
        showLoading.value = isLoading
    }

    var isInvalidToken = MutableLiveData<Boolean>()
    fun isInvalidToken(isLoading: Boolean) {
        isInvalidToken.value = isLoading
    }

    var onBackPressed = MutableLiveData<Boolean>()
    fun onBackPressed(isBackPressed: Boolean) {
        onBackPressed.value = isBackPressed
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("AppBaseViewModel Cleared")
    }

    fun clearUserSession(): Boolean {
        return true
    }

}
