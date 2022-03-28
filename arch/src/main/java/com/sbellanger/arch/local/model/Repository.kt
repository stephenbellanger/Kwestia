package com.sbellanger.arch.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Repository(
    @PrimaryKey
    var id: Int,
    var name: String,
    var description: String,
    var language: String,
    var openIssuesCount: Int,
    var repositoryName: String
)
