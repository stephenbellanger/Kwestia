package com.sbellanger.issue.di

import com.sbellanger.issue.data.IIssueRepository
import com.sbellanger.issue.data.IssueRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class IssueModule {

    @Binds
    abstract fun bindIIssueRepository(
        issueRepository: IssueRepository
    ): IIssueRepository
}
