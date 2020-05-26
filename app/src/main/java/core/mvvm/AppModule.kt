package core.mvvm

import android.content.Context
import com.azmin.skelton.utils.SharedPreferenceUtils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    fun provideSharedPreference(context: Context): SharedPreferenceUtils {
        return SharedPreferenceUtils(context)
    }

}