package com.sbellanger.favorite.presentation

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
abstract class FavoriteFragmentModule {

    /*@Binds
    abstract fun bindIFavoriteContractViewModel(
        favoriteViewModel: FavoriteViewModel
    ): IFavoriteContract.ViewModel*/

    @Binds
    abstract fun bindIFavoriteContractViewNavigation(
        favoriteNavigator: FavoriteNavigator
    ): IFavoriteContract.ViewNavigation
}
