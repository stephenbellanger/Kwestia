package com.sbellanger.favorite.di

import com.sbellanger.favorite.data.FavoriteDataStore
import com.sbellanger.favorite.data.FavoriteRepository
import com.sbellanger.favorite.data.IFavoriteDataStore
import com.sbellanger.favorite.data.IFavoriteRepository
import toothpick.config.Module

class FavoriteModule : Module() {
    init {
        bind(IFavoriteRepository::class.java)
            .to(FavoriteRepository::class.java)
            .singleton()

        bind(IFavoriteDataStore::class.java)
            .to(FavoriteDataStore::class.java)
            .singleton()
    }
}
