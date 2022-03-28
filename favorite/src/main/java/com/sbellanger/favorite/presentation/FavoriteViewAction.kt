package com.sbellanger.favorite.presentation

sealed class FavoriteViewAction {
    data class RepositoryRemoved(val id: Int) : FavoriteViewAction()
    data class GoToIssue(val repositoryName: String, val issueCount: Int) : FavoriteViewAction()
}
