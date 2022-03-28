package com.sbellanger.arch.activity

import androidx.viewbinding.ViewBinding
import toothpick.config.Module
import toothpick.ktp.KTP
import toothpick.smoothie.module.SmoothieAndroidXActivityModule

abstract class KtpBaseActivity<VB : ViewBinding> : BaseActivity() {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    abstract var binding: VB

    ///////////////////////////////////////////////////////////////////////////
    // CONFIGURATION
    ///////////////////////////////////////////////////////////////////////////

    abstract val modules: Array<Module>

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    @Suppress("SpreadOperator")
    override fun injectDependencies() {
        KTP.openRootScope()
            .openSubScope(this)
            .installModules(SmoothieAndroidXActivityModule(this))
            .installModules(*modules)
            .inject(this)
    }

    override fun handleViewContent() {
        setContentView(binding.root)
    }
}
