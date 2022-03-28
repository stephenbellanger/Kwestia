package com.sbellanger.favorite.presentation

import androidx.lifecycle.LiveData
import com.sbellanger.favorite.domain.model.FavoriteEntity

interface IFavoriteContract {
    interface ViewCapability {
        fun showLoader(shouldShow: Boolean)
        fun showFavorites(viewState: FavoriteViewState.Loaded)
        fun showError()
        fun showNoResult()
    }

    interface ViewModel {
        val viewState: LiveData<FavoriteViewState>
        val viewEvent: LiveData<FavoriteViewEvent>

        fun requestViewState()
        fun removeFavorite(id: Int)
    }

    interface ViewNavigation {
        fun showRepositoryIssues(repositoryName: String, issueCount: Int)
        fun showRemoveFavoriteFeedback()
    }

    interface ViewEvent {
        interface IFavoriteListener {
            fun onFavoriteClick(favorite: FavoriteEntity)
            fun onFavoriteRemovedClick(id: Int)
        }
    }
}
