package com.sbellanger.gitsearch.application

import android.app.Application
import com.sbellanger.arch.helper.timberConcreteClassLinkTag
import com.sbellanger.arch.local.di.RoomModule
import com.sbellanger.arch.network.di.NetworkModule
import com.sbellanger.di.RepositoryModule
import com.sbellanger.favorite.di.FavoriteModule
import com.sbellanger.issue.di.IssueModule
import timber.log.Timber
import toothpick.ktp.KTP
import toothpick.smoothie.module.SmoothieApplicationModule

class GitSearchApplication : Application() {

    ///////////////////////////////////////////////////////////////////////////
    // LIFECYCLE
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreate() {
        super.onCreate()
        setupLogging()
        setupToothPick()
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

    private fun setupToothPick() {
        KTP.openRootScope()
            .installModules(
                SmoothieApplicationModule(this),
                NetworkModule(),
                RoomModule(),
                IssueModule(),
                RepositoryModule(),
                FavoriteModule()
            )
            .inject(this)
    }
}
