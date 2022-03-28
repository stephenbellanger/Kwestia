package com.sbellanger.gitsearch.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.fragment.app.Fragment
import com.sbellanger.arch.navigation.IAppFragmentBuilder
import com.sbellanger.issue.presentation.IssueFragment
import javax.inject.Inject

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
class GitSearchFragmentBuilder @Inject constructor() : IAppFragmentBuilder {

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    override fun getIssueFragment(repositoryName: String, issueCount: Int): Fragment {
        return IssueFragment.newInstance(repositoryName, issueCount)
    }
}
