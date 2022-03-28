package com.sbellanger.gitsearch.application

import android.app.Application
import com.sbellanger.arch.helper.timberConcreteClassLinkTag
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class GitSearchApplication : Application() {

    ///////////////////////////////////////////////////////////////////////////
    // LIFECYCLE
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreate() {
        super.onCreate()
        setupLogging()
    }

    override fun onTerminate() {
        Timber.v(timberConcreteClassLinkTag)
        super.onTerminate()
    }

    ///////////////////////////////////////////////////////////////////////////
    // HELPER
    ///////////////////////////////////////////////////////////////////////////

    private fun setupLogging() {
        Timber.plant(Timber.DebugTree())
    }
}
