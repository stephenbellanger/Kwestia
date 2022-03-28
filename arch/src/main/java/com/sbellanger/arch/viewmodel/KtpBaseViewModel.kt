package com.sbellanger.arch.viewmodel

import android.app.Application
import androidx.annotation.CallSuper
import toothpick.ktp.KTP

abstract class KtpBaseViewModel(application: Application) : BaseViewModel(application) {

    ///////////////////////////////////////////////////////////////////////////
    // LIFECYCLE
    ///////////////////////////////////////////////////////////////////////////

    @CallSuper
    override fun onCreated() {
        super.onCreated()
        KTP.openRootScope()
            .openSubScope(this)
            .inject(this)
    }
}
