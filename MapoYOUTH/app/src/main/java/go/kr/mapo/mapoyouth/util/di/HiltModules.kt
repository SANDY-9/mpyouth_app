package go.kr.mapo.mapoyouth.util.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import go.kr.mapo.mapoyouth.network.MapoYouthService
import go.kr.mapo.mapoyouth.network.repository.VolunteerDataSource
import go.kr.mapo.mapoyouth.network.repository.VolunteerRepository
import go.kr.mapo.mapoyouth.network.repository.YouthDataSource
import go.kr.mapo.mapoyouth.network.repository.YouthRepository
import go.kr.mapo.mapoyouth.ui.common.OrganizationDetailsFragment
import go.kr.mapo.mapoyouth.ui.youth.YouthListAdapter
import go.kr.mapo.mapoyouth.util.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
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
            return@Interceptor it.proceed(request)
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
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
    fun provideYouthRepository(mapoYouthService: MapoYouthService) : YouthRepository {
      return YouthRepository(mapoYouthService)
    }

    @Singleton
    @Provides
    fun provideVolunteerRepository(mapoYouthService: MapoYouthService) : VolunteerRepository {
        return VolunteerRepository(mapoYouthService)
    }

}