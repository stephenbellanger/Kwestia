package com.sbellanger.data

import com.sbellanger.arch.local.model.Repository
import com.sbellanger.arch.network.request.BaseGithubRequest
import com.sbellanger.data.mapper.TransformRawRepositoryToDaoUseCase
import io.reactivex.Single
import javax.inject.Inject

class RepositoryRequest @Inject constructor() : BaseGithubRequest() {

    ///////////////////////////////////////////////////////////////////////////
    // CONFIGURATION
    ///////////////////////////////////////////////////////////////////////////

    override val baseUrl: String
        get() = "/search/repositories"

    ///////////////////////////////////////////////////////////////////////////
    // DEPENDENCY
    ///////////////////////////////////////////////////////////////////////////

    @Inject
    lateinit var transformRawRepositoryToDaoUseCase: TransformRawRepositoryToDaoUseCase

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC API
    ///////////////////////////////////////////////////////////////////////////

    fun execute(repository: String): Single<List<Repository>> {
        return api
            .getRepositories(getWebServiceUrl(), repository)
            .map { transformRawRepositoryToDaoUseCase.execute(it) }
    }
}
