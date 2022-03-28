package com.sbellanger.issue.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.lifecycle.LiveData

interface IIssueContract {
    interface ViewModel {
        val viewState: MutableState<IssueViewState>
        val viewEvent: LiveData<IssueViewEvent>
        val textInputState: State<String>

        fun requestViewState(repositoryName: String, issueNameFilter: String)
        fun setRepositoryName(repositoryName: String)
        fun setText(text: String)

        fun requestViewAction(viewAction: IssueViewAction)
    }

    interface ViewNavigation {
        fun goBack()
    }
}
