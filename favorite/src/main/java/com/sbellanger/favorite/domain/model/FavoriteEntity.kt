package com.sbellanger.favorite.domain.model

data class FavoriteEntity(
    val id: Int,
    val name: String,
    val description: String,
    val language: String,
    val openedIssues: Int,
    val color: Int,
    val repositoryName: String
)
