package com.sbellanger.arch.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sbellanger.arch.local.model.Issue
import com.sbellanger.arch.local.model.Repository

@Database(entities = [Repository::class, Issue::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repositoryDao(): RepositoryDao
}
