package com.azmin.skelton

import android.content.Context
import core.base.BaseNetworkModule
import core.mvvm.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        BaseNetworkModule::class,
        AppModule::class,
        AndroidInjectionModule::class,
        AppBuilderModule::class/*,
        RepositoryModule::class*/
    ]
)

@Singleton
interface AppComponent {

    fun inject(app: AppApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: AppApplication): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

}