package com.sbellanger.arch.activity

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import com.sbellanger.arch.helper.timberConcreteClassLinkTag
import timber.log.Timber

abstract class BaseActivity : AppCompatActivity() {

    ///////////////////////////////////////////////////////////////////////////
    // LIFECYCLE
    ///////////////////////////////////////////////////////////////////////////

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.v(timberConcreteClassLinkTag)
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
        super.onDestroy()
    }

    ///////////////////////////////////////////////////////////////////////////
    // HELPER
    ///////////////////////////////////////////////////////////////////////////

    abstract fun handleViewContent()
}
