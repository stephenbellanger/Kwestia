package com.sbellanger.favorite.data

import com.sbellanger.arch.local.model.Repository
import io.reactivex.Completable
import io.reactivex.Observable

interface IFavoriteRepository {
    fun getFavorites(): Observable<List<Repository>>
    fun removeFavorite(id: Int): Completable
}
