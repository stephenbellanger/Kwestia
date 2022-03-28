package com.sbellanger.arch.network.di

import com.sbellanger.arch.network.IWsConfig
import com.sbellanger.arch.network.api.GithubApiFactory
import com.sbellanger.arch.network.api.GithubWsConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import toothpick.config.Module

class NetworkModule : Module() {
    init {
        // Configuration
        bind(IWsConfig::class.java)
            .toInstance(GithubWsConfig)

        // Factory
        bind(GithubApiFactory::class.java)
            .to(GithubApiFactory::class.java)
            .singleton()

        // App Api
        bind(Retrofit::class.java)
            .toProvider(RetrofitProvider::class.java)

        // Http Client
        bind(OkHttpClient::class.java)
            .toProvider(HttpClientProvider::class.java)
            .providesSingleton()
    }
}
