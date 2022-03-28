package com.sbellanger.presentation

sealed class RepositoryViewEvent {
    object RepositoryAdded : RepositoryViewEvent()
    object RepositoryRemoved : RepositoryViewEvent()
    data class GoToIssue(val repositoryName: String, val issueCount: Int) : RepositoryViewEvent()
}
