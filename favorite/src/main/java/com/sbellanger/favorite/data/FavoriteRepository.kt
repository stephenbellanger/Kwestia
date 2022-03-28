package com.sbellanger.favorite.data

import com.sbellanger.arch.local.model.Repository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class FavoriteRepository @Inject constructor() : IFavoriteRepository {

    ///////////////////////////////////////////////////////////////////////////
    // DEPENDENCY
    ///////////////////////////////////////////////////////////////////////////

    @Inject
    lateinit var dataStore: IFavoriteDataStore

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    override fun getFavorites(): Observable<List<Repository>> {
        return dataStore.getFavorites()
    }

    override fun removeFavorite(id: Int): Completable {
        return dataStore.removeFavorite(id)
    }
}
