package com.sbellanger.issue.presentation

import com.sbellanger.issue.domain.model.IssueEntity

sealed class IssueViewState {
    object Loading : IssueViewState()
    data class Loaded(
        val issues: List<IssueEntity>
    ) : IssueViewState()

    object Error : IssueViewState()
    object NoIssue : IssueViewState()
    object Init : IssueViewState()
}
