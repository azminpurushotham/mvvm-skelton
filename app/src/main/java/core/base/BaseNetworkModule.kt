package core.base

import android.content.Context
import core.webapis.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BaseNetworkModule {

    @Provides
    @Singleton
    fun provideApiService(context: Context): ApiService {
        return ApiService(context)
    }
}