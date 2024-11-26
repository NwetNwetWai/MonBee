package com.hana.data.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.hana.data.network.APIManager
import com.hana.data.network.APIManagerInterface
import com.hana.data.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHTTPClient(
        @ApplicationContext context: Context,
    ): OkHttpClient {

        val builder = OkHttpClient.Builder()
        builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        builder.addInterceptor(ChuckerInterceptor(context))
        builder.followRedirects(false)
        builder.followSslRedirects(false)
        builder.callTimeout(2, TimeUnit.MINUTES)
        return builder.build()
    }
    @Provides
    @Singleton
    fun provideApiService(httpClient: OkHttpClient): ApiService {
        return ApiService.create(httpClient)
    }

    @Provides
    @Singleton
    fun provideAPIManager(apiManager: APIManager): APIManagerInterface {
        return apiManager
    }



}
