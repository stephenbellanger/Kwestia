package com.sbellanger.arch.local.di

import android.app.Application
import androidx.room.Room
import com.sbellanger.arch.local.AppDatabase
import javax.inject.Inject
import javax.inject.Provider

class RoomDatabaseProvider @Inject constructor() : Provider<AppDatabase> {

    companion object {
        private const val DATABASE_NAME = "git_search_db"
    }

    ///////////////////////////////////////////////////////////////////////////
    // DEPENDENCY
    ///////////////////////////////////////////////////////////////////////////

    @Inject
    lateinit var application: Application

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    override fun get(): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}
