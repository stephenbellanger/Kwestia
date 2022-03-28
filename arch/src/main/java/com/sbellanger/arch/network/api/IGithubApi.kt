package com.sbellanger.arch.network.api

import com.sbellanger.arch.network.model.RawIssue
import com.sbellanger.arch.network.model.RawRepository
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface IGithubApi {

    @GET
    fun getIssues(
        @Url url: String,
        @Query("q") query: String
    ): Single<List<RawIssue>>

    @GET
    fun getRepositories(
        @Url url: String,
        @Query("q") query: String
    ): Single<RawRepository>
}
