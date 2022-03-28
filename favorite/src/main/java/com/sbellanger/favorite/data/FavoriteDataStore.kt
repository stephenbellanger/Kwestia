package com.sbellanger.favorite.data

import com.sbellanger.arch.local.RepositoryDao
import com.sbellanger.arch.local.model.Repository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class FavoriteDataStore @Inject constructor() : IFavoriteDataStore {

    ///////////////////////////////////////////////////////////////////////////
    // DEPENDENCY
    ///////////////////////////////////////////////////////////////////////////

    @Inject
    lateinit var database: RepositoryDao

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    override fun getFavorites(): Observable<List<Repository>> {
        return database.getRepositories()
    }

    override fun removeFavorite(id: Int): Completable {
        return database.removeRepository(id)
    }
}
