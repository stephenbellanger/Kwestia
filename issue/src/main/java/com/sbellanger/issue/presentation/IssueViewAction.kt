package com.sbellanger.issue.presentation

sealed class IssueViewAction {
    object GoBack : IssueViewAction()
    object ClearSearch : IssueViewAction()
}
