package com.sbellanger.arch.fragment

import com.sbellanger.arch.helper.timberConcreteClassLinkTag
import timber.log.Timber
import toothpick.config.Module
import toothpick.ktp.KTP
import toothpick.smoothie.module.SmoothieAndroidXActivityModule

abstract class KtpBaseFragment : BaseFragment() {

    ///////////////////////////////////////////////////////////////////////////
    // LIFECYCLE
    ///////////////////////////////////////////////////////////////////////////

    abstract val modules: Array<Module>

    override fun injectDependencies() {
        Timber.v(timberConcreteClassLinkTag)
        KTP.openRootScope()
            .openSubScope(requireActivity())
            .installModules(SmoothieAndroidXActivityModule(requireActivity()))
            .openSubScope(this)
            .installModules(*modules)
            .inject(this)
    }
}
