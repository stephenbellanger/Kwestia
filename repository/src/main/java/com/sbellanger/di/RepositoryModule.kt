package com.sbellanger.di

import com.sbellanger.data.IRepositoryDataStore
import com.sbellanger.data.IRepositoryRepository
import com.sbellanger.data.RepositoryDataStore
import com.sbellanger.data.RepositoryRepository
import toothpick.config.Module

class RepositoryModule : Module() {
    init {
        bind(IRepositoryRepository::class.java)
            .to(RepositoryRepository::class.java)
            .singleton()

        bind(IRepositoryDataStore::class.java)
            .to(RepositoryDataStore::class.java)
            .singleton()
    }
}
