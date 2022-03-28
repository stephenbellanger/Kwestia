package com.sbellanger.issue.presentation

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
abstract class IssueFragmentModule {

    /*@Binds
    abstract fun bindIIssueContractViewModel(
        issueViewModel: IssueViewModel
    ): IIssueContract.ViewModel*/

    @Binds
    abstract fun bindIIssueContractViewNavigation(
        issueNavigator: IssueNavigator
    ): IIssueContract.ViewNavigation
}
