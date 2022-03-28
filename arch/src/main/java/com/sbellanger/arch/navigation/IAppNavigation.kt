package com.sbellanger.arch.navigation

import androidx.fragment.app.Fragment

interface IAppNavigation {
    fun addFragment(fragment: Fragment)
    fun showSnackbar(message: String)
    fun goBack()
}
