package com.azmin.skelton.home

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector


@Module(includes = [HomeModule::class])
abstract class HomeBuilderModule {

    @ContributesAndroidInjector(modules = [InjectorViewModel::class])
    abstract fun bind(): HomeFragment

    /*Inject ViewModel started*/
    @Module
    class InjectorViewModel {
        @Provides
        fun provideViewModel(
            factory: ViewModelProvider.Factory,
            target: HomeFragment
        ) = ViewModelProvider(target, factory).get(HomeVM::class.java)
    }

}