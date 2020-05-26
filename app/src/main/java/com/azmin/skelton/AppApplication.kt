package com.azmin.skelton

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.utility.InternetUtil
import core.utility.LocaleHelper
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class AppApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Any>

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LocaleHelper.onAttach(base, LocaleHelper.getLanguage(base)))
        MultiDex.install(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        InternetUtil.init(this)
        DaggerAppComponent.builder()
            .application(this)
            .context(this)
            .build().inject(this)

    }

    /** Returns an [AndroidInjector].  */
    override fun androidInjector(): AndroidInjector<Any> {
        return injector
    }
}
