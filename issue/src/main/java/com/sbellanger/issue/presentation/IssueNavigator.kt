package com.sbellanger.issue.presentation

import com.sbellanger.arch.navigation.IAppNavigation
import javax.inject.Inject

class IssueNavigator @Inject constructor() : IIssueContract.ViewNavigation {

    ///////////////////////////////////////////////////////////////////////////
    // DEPENDENCY
    ///////////////////////////////////////////////////////////////////////////

    @Inject
    lateinit var appNavigation: IAppNavigation

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    override fun goBack() {
        appNavigation.goBack()
    }
}
