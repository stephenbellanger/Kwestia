package com.sbellanger.di

import com.sbellanger.data.IRepositoryDataStore
import com.sbellanger.data.IRepositoryRepository
import com.sbellanger.data.RepositoryDataStore
import com.sbellanger.data.RepositoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindIRepositoryRepository(
        repositoryRepository: RepositoryRepository
    ): IRepositoryRepository

    @Binds
    abstract fun bindIRepositoryDataStore(
        repositoryDataStore: RepositoryDataStore
    ): IRepositoryDataStore
}
