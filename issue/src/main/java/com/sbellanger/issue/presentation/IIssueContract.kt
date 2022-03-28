package com.sbellanger.issue.presentation

import androidx.lifecycle.LiveData
import com.sbellanger.issue.domain.model.IssueEntity

interface IIssueContract {
    interface ViewCapability {
        fun setTitle(repositoryName: String)
        fun setIssueIndicator(issueNumber: Int)
        fun showIssues(issues: List<IssueEntity>)
        fun showNoIssue()
    }

    interface ViewModel {
        val viewState: LiveData<IssueViewState>

        fun requestViewState(repositoryName: String, issueNameFilter: String)
        fun setRepositoryName(repositoryName: String)
        fun setText(text: String)
    }

    interface ViewNavigation {
        fun goBack()
    }
}
