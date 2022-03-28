package com.sbellanger.arch.network.request

import com.sbellanger.arch.network.IWsConfig

abstract class BaseRequest<API> {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    //////////////////////////////////////////////////////////////////////////

    protected abstract val api: API
    protected abstract val baseUrl: String
    protected abstract val wsConfig: IWsConfig
}
