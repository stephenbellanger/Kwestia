package com.sbellanger.favorite.domain.usecase

import com.sbellanger.favorite.presentation.FavoriteViewState
import io.reactivex.Observable
import javax.inject.Inject

class GetFavoriteViewStateUseCase @Inject constructor() {

    ///////////////////////////////////////////////////////////////////////////
    // DEPENDENCY
    ///////////////////////////////////////////////////////////////////////////

    @Inject
    lateinit var getFavoritesUseCase: GetFavoritesUseCase

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC API
    ///////////////////////////////////////////////////////////////////////////

    fun execute(): Observable<FavoriteViewState> {
        return getFavoritesUseCase
            .execute()
            .map { favorites ->
                if (favorites.isNotEmpty()) {
                    FavoriteViewState.Loaded(favorites)
                } else {
                    FavoriteViewState.NoResult
                }
            }
            .onErrorReturnItem(FavoriteViewState.Error)
    }
}
