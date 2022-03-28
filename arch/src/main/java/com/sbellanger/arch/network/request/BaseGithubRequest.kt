package com.sbellanger.arch.network.request

import com.sbellanger.arch.network.IWsConfig
import com.sbellanger.arch.network.api.GithubApiFactory
import com.sbellanger.arch.network.api.IGithubApi
import toothpick.ktp.KTP

abstract class BaseGithubRequest : BaseRequest<IGithubApi>() {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private val githubApiFactory: GithubApiFactory
        get() = KTP
            .openRootScope()
            .getInstance(GithubApiFactory::class.java)

    override val api: IGithubApi
        get() = githubApiFactory.api

    override val wsConfig: IWsConfig
        get() = KTP
            .openRootScope()
            .getInstance(IWsConfig::class.java)

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC API
    ///////////////////////////////////////////////////////////////////////////

    protected open fun getWebServiceUrl(): String {
        return wsConfig.getBaseUrl() + baseUrl
    }
}
