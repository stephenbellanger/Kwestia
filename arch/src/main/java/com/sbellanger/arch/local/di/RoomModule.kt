package com.sbellanger.arch.local.di

import com.sbellanger.arch.local.AppDatabase
import com.sbellanger.arch.local.RepositoryDao
import toothpick.config.Module

class RoomModule : Module() {
    init {
        bind(AppDatabase::class.java)
            .toProvider(RoomDatabaseProvider::class.java)
            .providesSingleton()

        bind(RepositoryDao::class.java)
            .toProvider(RepositoryDaoProvider::class.java)
            .providesSingleton()
    }
}
