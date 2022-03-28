package com.sbellanger.data

import com.sbellanger.arch.local.RepositoryDao
import com.sbellanger.arch.local.model.Repository
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryDataStore @Inject constructor() : IRepositoryDataStore {

    ///////////////////////////////////////////////////////////////////////////
    // DEPENDENCY
    ///////////////////////////////////////////////////////////////////////////

    @Inject
    lateinit var database: RepositoryDao

    private val temporaryRepository = mutableListOf<Repository>()

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    override fun getRepositories(): Observable<List<Repository>> {
        return database.getRepositories()
    }

    override fun addRepository(repository: Repository): Completable {
        return database.insertRepository(repository)
    }

    override fun removeRepository(id: Int): Completable {
        return database.removeRepository(id)
    }

    override fun saveTemporaryRepository(repositories: List<Repository>): Completable {
        return Completable.fromAction {
            temporaryRepository.apply {
                clear()
                addAll(repositories)
            }
        }
    }

    override fun getTemporaryRepository(): Maybe<List<Repository>> {
        return Maybe.just(temporaryRepository)
    }
}
