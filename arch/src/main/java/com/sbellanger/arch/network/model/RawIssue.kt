package com.sbellanger.arch.network.model

import com.google.gson.annotations.SerializedName

data class RawIssue(
    @SerializedName("id") var id: Int?,
    @SerializedName("title") var title: String?,
    @SerializedName("body") var body: String?,
    @SerializedName("number") var number: Int?,
    @SerializedName("user") var user: RawUserIssue?
)

data class RawUserIssue(
    @SerializedName("id") var id: Int?,
    @SerializedName("login") var login: String?,
    @SerializedName("avatar_url") var pictureProfileUrl: String?
)
