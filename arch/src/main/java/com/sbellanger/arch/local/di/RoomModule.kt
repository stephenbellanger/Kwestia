package com.sbellanger.arch.local.di

import android.app.Application
import androidx.room.Room
import com.sbellanger.arch.local.AppDatabase
import com.sbellanger.arch.local.RepositoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DATABASE_NAME = "git_search_db"

    @Singleton
    @Provides
    fun provideAppDatabase(
        application: Application
    ): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideRepositoryDao(
        roomDatabase: AppDatabase
    ): RepositoryDao {
        return roomDatabase.repositoryDao()
    }
}
