package com.azmin.skelton.home

import androidx.lifecycle.ViewModel
import com.azmin.skelton.AppApplication
import com.azmin.skelton.utils.SharedPreferenceUtils
import core.mvvm.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class HomeModule {

    @Provides
    @IntoMap
    @ViewModelKey(HomeVM::class)
    fun provideHomeVM(
        application: AppApplication,/*lifecycle: Lifecycle,*/
        pref: SharedPreferenceUtils,
        repo: HomeRepo
    ): ViewModel {
        return HomeVM(application,/*lifecycle,*/pref, repo)
    }
}