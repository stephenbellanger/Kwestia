package com.sbellanger.arch.local.di

import com.sbellanger.arch.local.AppDatabase
import com.sbellanger.arch.local.RepositoryDao
import javax.inject.Inject
import javax.inject.Provider

class RepositoryDaoProvider @Inject constructor() : Provider<RepositoryDao> {

    ///////////////////////////////////////////////////////////////////////////
    // DEPENDENCY
    ///////////////////////////////////////////////////////////////////////////

    @Inject
    lateinit var roomDatabase: AppDatabase

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    override fun get(): RepositoryDao {
        return roomDatabase.repositoryDao()
    }
}
