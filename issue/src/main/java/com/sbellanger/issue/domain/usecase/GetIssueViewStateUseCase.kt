package com.sbellanger.issue.domain.usecase

import com.sbellanger.issue.presentation.IssueViewState
import io.reactivex.Single
import javax.inject.Inject

class GetIssueViewStateUseCase @Inject constructor() {

    ///////////////////////////////////////////////////////////////////////////
    // DEPENDENCY
    ///////////////////////////////////////////////////////////////////////////

    @Inject
    lateinit var getIssuesByRepositoryUseCase: GetIssuesByRepositoryUseCase

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC API
    ///////////////////////////////////////////////////////////////////////////

    fun execute(repositoryName: String, issueNameFilter: String): Single<IssueViewState> {
        return getIssuesByRepositoryUseCase
            .execute(repositoryName, issueNameFilter)
            .map { issues ->
                if (issues.isNotEmpty()) {
                    IssueViewState.Loaded(issues)
                } else {
                    IssueViewState.NoIssue
                }
            }
        .onErrorReturnItem(IssueViewState.Error)
    }
}
