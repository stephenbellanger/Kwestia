package com.sbellanger.issue.di

import com.sbellanger.issue.data.IIssueRepository
import com.sbellanger.issue.data.IssueRepository
import toothpick.config.Module

class IssueModule : Module() {
    init {
        bind(IIssueRepository::class.java)
            .to(IssueRepository::class.java)
            .singleton()
    }
}
