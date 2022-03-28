package com.sbellanger.arch.navigation

import androidx.fragment.app.Fragment

interface IAppFragmentBuilder {
    fun getIssueFragment(repositoryName: String, issueCount: Int): Fragment
}
