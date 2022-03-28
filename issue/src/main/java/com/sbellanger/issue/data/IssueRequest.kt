package com.sbellanger.issue.data

import com.sbellanger.arch.local.model.Issue
import com.sbellanger.arch.network.request.BaseGithubRequest
import com.sbellanger.issue.data.mapper.TransformRawIssueToDaoUseCase
import io.reactivex.Single
import javax.inject.Inject

class IssueRequest @Inject constructor() : BaseGithubRequest() {

    ///////////////////////////////////////////////////////////////////////////
    // CONFIGURATION
    ///////////////////////////////////////////////////////////////////////////

    override val baseUrl: String
        get() = "/repos/"

    ///////////////////////////////////////////////////////////////////////////
    // DEPENDENCY
    ///////////////////////////////////////////////////////////////////////////

    @Inject
    lateinit var transformRawIssueToDaoUseCase: TransformRawIssueToDaoUseCase

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC API
    ///////////////////////////////////////////////////////////////////////////

    fun execute(repositoryName: String, issueNameFilter: String): Single<List<Issue>> {
        return api
            .getIssues(getWebServiceUrl(repositoryName), issueNameFilter)
            .map { transformRawIssueToDaoUseCase.execute(it) }
    }

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    private fun getWebServiceUrl(repositoryName: String): String {
        return super.getWebServiceUrl() + repositoryName + "/issues"
    }

}
