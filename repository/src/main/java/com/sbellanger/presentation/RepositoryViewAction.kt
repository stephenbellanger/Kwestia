package com.sbellanger.presentation

import com.sbellanger.domain.model.RepositoryEntity

sealed class RepositoryViewAction {
    data class RepositoryAdded(
        val repository: RepositoryEntity
    ) : RepositoryViewAction()

    data class RepositoryRemoved(
        val repository: RepositoryEntity
    ) : RepositoryViewAction()

    data class GoToIssue(
        val repositoryName: String,
        val issueCount: Int
    ) : RepositoryViewAction()

    object ClearSearch : RepositoryViewAction()
}
