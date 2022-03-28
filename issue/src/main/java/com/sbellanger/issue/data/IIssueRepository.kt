package com.sbellanger.issue.data

import com.sbellanger.arch.local.model.Issue
import io.reactivex.Single

interface IIssueRepository {
    fun getIssues(repositoryName: String, issueNameFilter: String): Single<List<Issue>>
}
