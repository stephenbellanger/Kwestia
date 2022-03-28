package com.sbellanger.arch.network.api

import com.sbellanger.arch.network.IWsConfig

object GithubWsConfig : IWsConfig {

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    override fun getBaseUrl(): String {
        return "https://api.github.com"
    }
}
