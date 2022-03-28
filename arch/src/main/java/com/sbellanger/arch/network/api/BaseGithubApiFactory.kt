package com.sbellanger.arch.network.api

import com.sbellanger.arch.network.BaseApiFactory
import com.sbellanger.arch.network.IWsConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import toothpick.ktp.KTP

abstract class BaseGithubApiFactory<API> : BaseApiFactory<API>() {

    ///////////////////////////////////////////////////////////////////////////
    // DEPENDENCY
    ///////////////////////////////////////////////////////////////////////////

    override val configWs: IWsConfig
        get() = KTP
            .openRootScope()
            .getInstance(IWsConfig::class.java)

    override val httpClient: OkHttpClient
        get() = KTP
            .openRootScope()
            .getInstance(OkHttpClient::class.java)

    override val retrofit: Retrofit
        get() = KTP
            .openRootScope()
            .getInstance(Retrofit::class.java)
}
