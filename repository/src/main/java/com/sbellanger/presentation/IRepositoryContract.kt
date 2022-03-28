package com.sbellanger.presentation

import androidx.compose.runtime.State
import androidx.lifecycle.LiveData
import com.sbellanger.domain.model.RepositoryEntity

interface IRepositoryContract {
    interface ViewModel {
        val viewState: State<RepositoryViewState>
        val viewEvent: LiveData<RepositoryViewEvent>
        val textInputState: State<String>

        fun requestViewState(name: String)
        fun removeRepository(id: Int)
        fun addRepository(repository: RepositoryEntity)

        fun setText(text: String)

        fun requestViewAction(viewAction: RepositoryViewAction)
    }

    interface ViewNavigation {
        fun showRepositoryAddedFeedback()
        fun showRepositoryRemovedFeedback()
        fun showRepositoryIssues(repositoryName: String, issueCount: Int)
    }
}

