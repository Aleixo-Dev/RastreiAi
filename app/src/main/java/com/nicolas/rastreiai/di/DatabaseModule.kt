package com.nicolas.rastreiai.di

import android.app.Application
import androidx.room.Room
import com.nicolas.rastreiai.data.data_source.local.LocalDataSource
import com.nicolas.rastreiai.data.data_source.local.LocalDataSourceImpl
import com.nicolas.rastreiai.data.data_source.local.db.OrderDao
import com.nicolas.rastreiai.data.data_source.local.db.OrderDatabase
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
object DatabaseModule {

    @Provides
    @Singleton
    fun provideOrderDatabase(app: Application): OrderDatabase {
        return Room.databaseBuilder(
            app,
            OrderDatabase::class.java,
            OrderDatabase.DATABASE_NAME
        ).allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun provideLocalRepository(db: OrderDatabase): LocalDataSource {
        return LocalDataSourceImpl(db.orderDao)
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(dao: OrderDatabase): OrderDao {
        return dao.orderDao
    }
}