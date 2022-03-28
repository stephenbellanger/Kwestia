package com.sbellanger.issue.data

import com.sbellanger.arch.local.model.Issue
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IssueRepository @Inject constructor() : IIssueRepository {

    ///////////////////////////////////////////////////////////////////////////
    // DEPENDENCY
    ///////////////////////////////////////////////////////////////////////////

    @Inject
    lateinit var request: IssueRequest

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    override fun getIssues(
        repositoryName: String,
        issueNameFilter: String
    ): Single<List<Issue>> {
        return request.execute(repositoryName, issueNameFilter)
    }
}
