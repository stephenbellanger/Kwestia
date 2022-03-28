package com.sbellanger.favorite.presentation

import androidx.compose.runtime.State
import androidx.lifecycle.LiveData

interface IFavoriteContract {
    interface ViewModel {
        val viewState: State<FavoriteViewState>
        val viewEvent: LiveData<FavoriteViewEvent>

        fun requestViewState()
        fun removeFavorite(id: Int)
        fun requestViewAction(viewAction: FavoriteViewAction)
    }

    interface ViewNavigation {
        fun showRepositoryIssues(repositoryName: String, issueCount: Int)
        fun showRemoveFavoriteFeedback()
    }
}
