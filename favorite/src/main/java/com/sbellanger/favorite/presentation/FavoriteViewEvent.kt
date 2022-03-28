package com.sbellanger.favorite.presentation

sealed class FavoriteViewEvent {
    object RepositoryRemoved : FavoriteViewEvent()
}
