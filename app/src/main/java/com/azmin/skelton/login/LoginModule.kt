package com.azmin.skelton.login

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import com.azmin.skelton.AppApplication
import com.azmin.skelton.utils.SharedPreferenceUtils
import core.mvvm.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class LoginModule {

    @Provides
    @IntoMap
    @ViewModelKey(LoginVM::class)
    fun provideLoginVM(
        application: AppApplication,/*lifecycle: Lifecycle,*/
        pref: SharedPreferenceUtils,
        repo: LoginRepo
    ): ViewModel {
        return LoginVM(application,/*lifecycle,*/pref, repo)
    }
}