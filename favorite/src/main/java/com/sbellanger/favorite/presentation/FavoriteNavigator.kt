package com.sbellanger.favorite.presentation

import android.content.res.Resources
import androidx.fragment.app.FragmentActivity
import com.sbellanger.arch.navigation.IAppFragmentBuilder
import com.sbellanger.arch.navigation.IAppNavigation
import com.sbellanger.favorite.R
import javax.inject.Inject

class FavoriteNavigator @Inject constructor() : IFavoriteContract.ViewNavigation {

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

    override fun showRepositoryIssues(repositoryName: String, issueCount: Int) {
        appNavigator.addFragment(
            appFragmentBuilder.getIssueFragment(repositoryName, issueCount)
        )
    }

    override fun showRemoveFavoriteFeedback() {
        appNavigator.showSnackbar(resources.getString(R.string.favorite_removed_feedback))
    }
}
