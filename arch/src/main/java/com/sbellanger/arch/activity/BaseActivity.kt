package com.sbellanger.arch.activity

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import com.sbellanger.arch.helper.timberConcreteClassLinkTag
import timber.log.Timber
import toothpick.ktp.KTP
import toothpick.smoothie.module.SmoothieAndroidXActivityModule

abstract class BaseActivity : AppCompatActivity() {

    ///////////////////////////////////////////////////////////////////////////
    // LIFECYCLE
    ///////////////////////////////////////////////////////////////////////////

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.v(timberConcreteClassLinkTag)
        injectDependencies()
        handleViewContent()
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        Timber.v(timberConcreteClassLinkTag)
    }

    @CallSuper
    override fun onPause() {
        Timber.v(timberConcreteClassLinkTag)
        super.onPause()
    }

    @CallSuper
    override fun onDestroy() {
        Timber.v(timberConcreteClassLinkTag)
        KTP.closeScope(this)
        super.onDestroy()
    }

    ///////////////////////////////////////////////////////////////////////////
    // HELPER
    ///////////////////////////////////////////////////////////////////////////

    abstract fun handleViewContent()

    // As this Activity is used as parent scope of Fragment scope
    // We must init it scope at onCreate and close at OnDestroy, (the fragment will not)
    // Injection is not done, this can be overridden to handle Ktp mechanisms
    open fun injectDependencies() {
        KTP.openRootScope()
            .openSubScope(this)
            .installModules(SmoothieAndroidXActivityModule(this))
    }
}
