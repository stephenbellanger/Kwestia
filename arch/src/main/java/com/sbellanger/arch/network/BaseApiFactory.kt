package com.sbellanger.arch.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit

abstract class BaseApiFactory<API> {

    ///////////////////////////////////////////////////////////////////////////
    // DEPENDENCY
    ///////////////////////////////////////////////////////////////////////////

    abstract val configWs: IWsConfig
    abstract val httpClient: OkHttpClient
    abstract val retrofit: Retrofit
    abstract val api: API

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC API
    ///////////////////////////////////////////////////////////////////////////

    protected abstract fun buildApi(): API
}
