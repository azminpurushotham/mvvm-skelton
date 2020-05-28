package com.azmin.skelton

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.azmin.skelton.home.HomeBuilderModule
import com.azmin.skelton.itemDetail.ItemDetailBuilderModule
import com.azmin.skelton.login.LoginBuilderModule
import core.mvvm.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Provider


@Module(
    includes = [
        LoginBuilderModule::class,
        HomeBuilderModule::class,
        ItemDetailBuilderModule::class
    ]
)
class AppBuilderModule {

    @Provides
    fun provideViewModelFactory(
        providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
    ): ViewModelProvider.Factory = ViewModelFactory(providers)

}