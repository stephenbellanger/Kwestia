package com.sbellanger.gitsearch.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar
import com.sbellanger.arch.navigation.IAppNavigation
import com.sbellanger.gitsearch.R
import javax.inject.Inject

class GitSearchNavigator @Inject constructor() : IAppNavigation {

    ///////////////////////////////////////////////////////////////////////////
    // DEPENDENCY
    ///////////////////////////////////////////////////////////////////////////

    @Inject
    lateinit var activity: FragmentActivity

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    override fun addFragment(fragment: Fragment) {
        activity
            .supportFragmentManager
            .beginTransaction()
            .add(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun showSnackbar(message: String) {
        Snackbar
            .make(
                activity.findViewById(android.R.id.content),
                message,
                Snackbar.LENGTH_SHORT
            )
            .setAnchorView(activity.findViewById(R.id.navigation_bar))
            .show()
    }

    override fun goBack() {
        activity
            .supportFragmentManager
            .popBackStack()
    }
}
