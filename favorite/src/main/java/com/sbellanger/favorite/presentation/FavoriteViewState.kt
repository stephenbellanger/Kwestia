package com.sbellanger.favorite.presentation

import com.sbellanger.favorite.domain.model.FavoriteEntity

sealed class FavoriteViewState {
    data class Loaded(
        val favorites: List<FavoriteEntity>
    ) : FavoriteViewState()

    object Loading : FavoriteViewState()
    object Error : FavoriteViewState()
    object NoResult : FavoriteViewState()
}
