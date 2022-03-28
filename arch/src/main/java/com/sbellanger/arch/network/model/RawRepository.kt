package com.sbellanger.arch.network.model

import com.google.gson.annotations.SerializedName

data class RawRepository(
    @SerializedName("items") val items: List<RawRepositoryItem>
)

data class RawRepositoryItem(
    @SerializedName("id") var id: Int?,
    @SerializedName("name") var name: String?,
    @SerializedName("full_name") var fullName: String?,
    @SerializedName("description") var description: String?,
    @SerializedName("open_issues_count") var openIssuesCount: Int?,
    @SerializedName("has_issues") var hasIssues: Boolean?,
    @SerializedName("language") var language: String?
)
