package com.azmin.skelton.itemDetail

import androidx.lifecycle.ViewModel
import com.azmin.skelton.AppApplication
import com.azmin.skelton.utils.SharedPreferenceUtils
import core.mvvm.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ItemDetailModule {

    @Provides
    @IntoMap
    @ViewModelKey(ItemDetailVM::class)
    fun provideHomeVM(
        application: AppApplication,/*lifecycle: Lifecycle,*/
        pref: SharedPreferenceUtils,
        repo: ItemDetailRepo
    ): ViewModel {
        return ItemDetailVM(application,/*lifecycle,*/pref, repo)
    }
}