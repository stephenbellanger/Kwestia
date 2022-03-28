package com.sbellanger.favorite.presentation

sealed class FavoriteViewEvent {
    object RepositoryRemoved : FavoriteViewEvent()
    data class GoToIssue(val repositoryName: String, val issueCount: Int) : FavoriteViewEvent()
}
