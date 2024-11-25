package com.hana.data.di

import android.content.Context
import androidx.room.Room
import com.hana.data.database.AppDatabase
import com.hana.data.database.dao.CustomerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app_database").build()
    }

    @Provides
    fun provideCustomerDao(database: AppDatabase): CustomerDao {
        return database.customerDao()
    }

}