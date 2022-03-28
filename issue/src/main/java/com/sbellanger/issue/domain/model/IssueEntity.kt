package com.sbellanger.issue.domain.model

data class IssueEntity(
    val name: String,
    val number: String,
    val description: String,
    val openedBy: String
)
