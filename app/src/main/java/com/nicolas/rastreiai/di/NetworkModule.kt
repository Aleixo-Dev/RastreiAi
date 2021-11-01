package com.nicolas.rastreiai.di

import com.nicolas.rastreiai.common.Constants
import com.nicolas.rastreiai.data.data_source.remote.PostmanApi
import com.nicolas.rastreiai.data.data_source.remote.PostmanRemoteDataSource
import com.nicolas.rastreiai.data.data_source.remote.PostmanRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitService() : PostmanApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .build()
            .create(PostmanApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient() : OkHttpClient{
        val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .connectTimeout(15, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideRemoteRepository(api : PostmanApi) : PostmanRemoteDataSource{
        return PostmanRemoteDataSourceImpl(api)
    }
}