package com.sbellanger.arch.network.request

import com.sbellanger.arch.network.IWsConfig
import com.sbellanger.arch.network.api.GithubApiFactory
import com.sbellanger.arch.network.api.IGithubApi
import javax.inject.Inject

abstract class BaseGithubRequest : BaseRequest<IGithubApi>() {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    @Inject
    lateinit var githubApiFactory: GithubApiFactory

    override val api: IGithubApi
        get() = githubApiFactory.api

    @Inject
    override lateinit var wsConfig: IWsConfig

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC API
    ///////////////////////////////////////////////////////////////////////////

    protected open fun getWebServiceUrl(): String {
        return wsConfig.getBaseUrl() + baseUrl
    }
}
