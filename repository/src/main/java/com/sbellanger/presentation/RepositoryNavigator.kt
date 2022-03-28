package com.sbellanger.presentation

import android.content.res.Resources
import androidx.fragment.app.FragmentActivity
import com.sbellanger.arch.navigation.IAppFragmentBuilder
import com.sbellanger.arch.navigation.IAppNavigation
import com.sbellanger.repository.R
import javax.inject.Inject

class RepositoryNavigator @Inject constructor() : IRepositoryContract.ViewNavigation {

    ///////////////////////////////////////////////////////////////////////////
    // DEPENDENCY
    ///////////////////////////////////////////////////////////////////////////

    @Inject
    lateinit var activity: FragmentActivity

    @Inject
    lateinit var resources: Resources

    @Inject
    lateinit var appNavigator: IAppNavigation

    @Inject
    lateinit var appFragmentBuilder: IAppFragmentBuilder

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    override fun showRepositoryAddedFeedback() {
        appNavigator.showSnackbar(resources.getString(R.string.repository_added_feedback))
    }

    override fun showRepositoryRemovedFeedback() {
        appNavigator.showSnackbar(resources.getString(R.string.repository_removed_feedback))
    }

    override fun showRepositoryIssues(repositoryName: String, issueCount: Int) {
        appNavigator.addFragment(
            appFragmentBuilder.getIssueFragment(repositoryName, issueCount)
        )
    }
}
