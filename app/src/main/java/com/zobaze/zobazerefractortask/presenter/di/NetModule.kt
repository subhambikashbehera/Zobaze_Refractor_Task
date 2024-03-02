package com.zobaze.zobazerefractortask.presenter.di

import com.zobaze.zobazerefractortask.BuildConfig
import com.zobaze.zobazerefractortask.data.api.ZobazeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * date - 3/02/2024 time - 10:06 am
 * Created By - Subham Bikash Behera
 * */

@Module
@InstallIn(SingletonComponent::class)
class NetModule {


    /** made custom client for logging and if we want to add header in api request*/
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {

        val interceptorAuth = Interceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            //optional for adding the header When we add access token for the api call
            chain.proceed(requestBuilder.build())
        }

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(interceptorAuth)
            .connectTimeout(30, TimeUnit.MINUTES)
            .readTimeout(20, TimeUnit.MINUTES)
            .writeTimeout(25, TimeUnit.MINUTES)
            .build()

    }

    /** we are creating the instance of the retrofit here for api calls*/
    @Singleton
    @Provides
    fun provideNetworkModule(okHttpClient: OkHttpClient): Retrofit {
        var retrofit: Retrofit? = null
        try {
            retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return retrofit!!
    }

    /** we are creating the instance of the ZobazeApiService here for api calls*/
    @Singleton
    @Provides
    fun provideSmartHomeService(retrofit: Retrofit): ZobazeApiService {
        return retrofit.create(ZobazeApiService::class.java)
    }


}