package com.sbellanger.issue.domain.usecase

import com.sbellanger.issue.data.IIssueRepository
import com.sbellanger.issue.domain.model.IssueEntity
import io.reactivex.Single
import javax.inject.Inject

class GetIssuesByRepositoryUseCase @Inject constructor() {

    ///////////////////////////////////////////////////////////////////////////
    // DEPENDENCY
    ///////////////////////////////////////////////////////////////////////////

    @Inject
    lateinit var repository: IIssueRepository

    @Inject
    lateinit var transformRawIssueToEntityUseCase: TransformRawIssueToEntityUseCase

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC API
    ///////////////////////////////////////////////////////////////////////////

    fun execute(repositoryName: String, issueNameFilter: String): Single<List<IssueEntity>> {
        return repository
            .getIssues(repositoryName, issueNameFilter)
            .map { transformRawIssueToEntityUseCase.execute(it) }
    }
}
