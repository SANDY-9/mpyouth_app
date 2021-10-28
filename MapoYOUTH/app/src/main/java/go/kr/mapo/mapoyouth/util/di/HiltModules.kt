package go.kr.mapo.mapoyouth.util.di

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import go.kr.mapo.mapoyouth.network.MapoYouthService
import go.kr.mapo.mapoyouth.network.NetworkState
import go.kr.mapo.mapoyouth.network.repository.*
import go.kr.mapo.mapoyouth.util.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-09-30
 * @desc
 */

@InstallIn(SingletonComponent::class)
@Module
object HiltModules {

    @Singleton
    @Provides
    fun provideRetrofitInterface() : MapoYouthService {
        val requestInterceptor = Interceptor {
            val url = it.request()
                .url()
                .newBuilder()
                .build()
            val request = it.request()
                .newBuilder()
                .url(url)
                .build()
            Log.e("[URL]", url.toString())
            return@Interceptor it.proceed(request)
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .build()
            .create(MapoYouthService::class.java)
    }

    @Singleton
    @Provides
    fun provideNetworkState(@ApplicationContext app: Context) = NetworkState(app)

    @Singleton
    @Provides
    fun provideYouthRepository(mapoYouthService: MapoYouthService) : YouthRepository {
      return YouthRepository(mapoYouthService)
    }

    @Singleton
    @Provides
    fun provideVolunteerRepository(mapoYouthService: MapoYouthService) : VolunteerRepository {
        return VolunteerRepository(mapoYouthService)
    }

    @Singleton
    @Provides
    fun provideEdurRepository(mapoYouthService: MapoYouthService) : EduRepository {
        return EduRepository(mapoYouthService)
    }

    @Singleton
    @Provides
    fun provideDonationRepository(mapoYouthService: MapoYouthService) : DonationRepository {
        return DonationRepository(mapoYouthService)
    }

}