package com.sbellanger.favorite.di

import com.sbellanger.favorite.data.FavoriteDataStore
import com.sbellanger.favorite.data.FavoriteRepository
import com.sbellanger.favorite.data.IFavoriteDataStore
import com.sbellanger.favorite.data.IFavoriteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FavoriteModule {

    @Binds
    abstract fun bindIFavoriteRepository(
        favoriteRepository: FavoriteRepository
    ): IFavoriteRepository

    @Binds
    abstract fun bindIFavoriteDataStore(
        favoriteDataStore: FavoriteDataStore
    ): IFavoriteDataStore
}
