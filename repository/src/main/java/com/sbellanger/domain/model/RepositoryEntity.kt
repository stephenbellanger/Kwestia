package com.sbellanger.domain.model

data class RepositoryEntity(
    val id: Int,
    val name: String,
    val description: String,
    val isFavorite: Boolean,
    val language: String,
    val openIssuesCount: Int,
    val repositoryName: String
)
