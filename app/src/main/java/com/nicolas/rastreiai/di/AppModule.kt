package com.nicolas.rastreiai.di

import com.nicolas.rastreiai.data.data_source.local.LocalDataSource
import com.nicolas.rastreiai.data.data_source.remote.PostmanRemoteDataSource
import com.nicolas.rastreiai.data.repository.PostmanRepositoryImpl
import com.nicolas.rastreiai.domain.repository.PostmanRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRepositoryUseCases(
        remote: PostmanRemoteDataSource,
        local: LocalDataSource
    ): PostmanRepository {
        return PostmanRepositoryImpl(remote, local)
    }
}