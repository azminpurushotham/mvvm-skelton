package com.azmin.skelton.login

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector


@Module(includes = [LoginModule::class])
abstract class LoginBuilderModule {

    @ContributesAndroidInjector(modules = [InjectorViewModel::class])
    abstract fun bind(): LoginActivity

    /*Inject ViewModel started*/
    @Module
    class InjectorViewModel {
        @Provides
        fun provideViewModel(
            factory: ViewModelProvider.Factory,
            target: LoginActivity
        ) = ViewModelProvider(target, factory).get(LoginVM::class.java)
    }

}