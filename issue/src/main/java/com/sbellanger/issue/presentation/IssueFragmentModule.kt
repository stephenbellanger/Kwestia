package com.sbellanger.issue.presentation

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import toothpick.config.Module
import toothpick.ktp.binding.bind

class IssueFragmentModule(fragment: Fragment) : Module() {
    init {
        bind<IIssueContract.ViewModel>()
            .toInstance(ViewModelProvider(fragment)[IssueViewModel::class.java])
        bind<IIssueContract.ViewNavigation>()
            .toClass<IssueNavigator>()
    }
}
