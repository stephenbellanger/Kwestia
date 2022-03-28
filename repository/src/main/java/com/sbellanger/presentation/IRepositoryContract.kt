package com.sbellanger.presentation

import androidx.lifecycle.LiveData
import com.sbellanger.domain.model.RepositoryEntity

interface IRepositoryContract {
    interface ViewCapability {
        fun showLoader(shouldShow: Boolean)
        fun showResults(viewState: RepositoryViewState.Loaded)
        fun showError()
        fun showNoResult()
    }

    interface ViewModel {
        val viewState: LiveData<RepositoryViewState>
        val viewEvent: LiveData<RepositoryViewEvent>

        fun requestViewState(name: String)
        fun removeRepository(id: Int)
        fun addRepository(repository: RepositoryEntity)

        fun setText(text: String)
    }

    interface ViewNavigation {
        fun showRepositoryAddedFeedback()
        fun showRepositoryRemovedFeedback()
        fun showRepositoryIssues(repositoryName: String, issueCount: Int)
    }

    interface ViewEvent {
        interface IRepositoryListener {
            fun onFavoriteClicked(repository: RepositoryEntity, isFavorite: Boolean)
            fun onRepositoryClicked(repository: RepositoryEntity)
        }
    }
}

