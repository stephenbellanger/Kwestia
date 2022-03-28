package com.sbellanger.data

import com.sbellanger.arch.local.model.Repository
import io.reactivex.Completable
import io.reactivex.Observable

interface IRepositoryRepository {
    fun requestRepositories(repository: String): Observable<List<Repository>>
    fun getRepositories(): Observable<List<Repository>>
    fun addRepository(repository: Repository): Completable
    fun removeRepository(id: Int): Completable
}
