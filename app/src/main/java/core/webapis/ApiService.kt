package core.webapis;

import android.content.Context
import com.azmin.skelton.BuildConfig
import com.azmin.skelton.utils.SharedPreferenceUtils
import com.google.gson.GsonBuilder
import dagger.Module
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class ApiService constructor(private val context: Context) {

    companion object {
        var retrofit: Retrofit? = null
    }


    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
//        httpClient.addInterceptor { chain ->
//            val ongoing = chain.request().newBuilder()
//            val token = GeneralRepository(context).getUser()?.token
//            if (token != null)
//                ongoing.addHeader("Authorization", "bearer $token")
//            //                ongoing.addHeader("OS", "Android")
//            //                ongoing.addHeader("appVersion", "1.0")
//            //                ongoing.addHeader("Content-Type", "application/json")
//            //                ongoing.addHeader("language", "ar")
//            //                val userEntity: UserEntity? = UserRepository(App.app).getUserEntity()
//            //                if (userEntity != null) {
//            //                    val session: String = userEntity.sessionKey.session
//            //                    ongoing.addHeader("session", session)
//            //                }
//            chain.proceed(ongoing.build())
//        }

        httpClient.addInterceptor(logging)
        httpClient.readTimeout(60, TimeUnit.SECONDS)
        httpClient.connectTimeout(60, TimeUnit.SECONDS)

        val gson = GsonBuilder()
            .setLenient()
            .create()

        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()


    }

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit?.create(serviceClass)!!
    }

}