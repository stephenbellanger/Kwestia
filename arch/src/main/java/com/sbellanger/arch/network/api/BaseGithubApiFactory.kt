package com.sbellanger.arch.network.api

import com.sbellanger.arch.network.BaseApiFactory
import com.sbellanger.arch.network.IWsConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Inject

abstract class BaseGithubApiFactory<API> : BaseApiFactory<API>() {

    ///////////////////////////////////////////////////////////////////////////
    // DEPENDENCY
    ///////////////////////////////////////////////////////////////////////////

    @Inject
    override lateinit var configWs: IWsConfig

    @Inject
    override lateinit var httpClient: OkHttpClient

    @Inject
    override lateinit var retrofit: Retrofit
}
