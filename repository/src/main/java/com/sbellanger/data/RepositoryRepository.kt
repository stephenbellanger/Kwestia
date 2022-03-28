package com.sbellanger.data

import com.sbellanger.arch.local.model.Repository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryRepository @Inject constructor() : IRepositoryRepository {

    ///////////////////////////////////////////////////////////////////////////
    // DEPENDENCY
    ///////////////////////////////////////////////////////////////////////////

    @Inject
    lateinit var request: RepositoryRequest

    @Inject
    lateinit var dataStore: IRepositoryDataStore

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private var previousQuery: String? = null

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    override fun requestRepositories(repository: String): Observable<List<Repository>> {
        return if (previousQuery != repository) {
            request
                .execute(repository)
                .doOnSuccess { previousQuery = repository }
                .flatMapCompletable { dataStore.saveTemporaryRepository(it) }
                .andThen(dataStore.getTemporaryRepository())
                .toObservable()
        } else dataStore.getTemporaryRepository().toObservable()
    }

    override fun getRepositories(): Observable<List<Repository>> {
        return dataStore.getRepositories()
    }

    override fun addRepository(repository: Repository): Completable {
        return dataStore.addRepository(repository)
    }

    override fun removeRepository(id: Int): Completable {
        return dataStore.removeRepository(id)
    }
}
