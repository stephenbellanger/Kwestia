package com.sbellanger.data

import com.sbellanger.arch.local.model.Repository
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable

interface IRepositoryDataStore {
    fun getRepositories(): Observable<List<Repository>>
    fun addRepository(repository: Repository): Completable
    fun removeRepository(id: Int): Completable
    fun saveTemporaryRepository(repositories: List<Repository>): Completable
    fun getTemporaryRepository(): Maybe<List<Repository>>
}
