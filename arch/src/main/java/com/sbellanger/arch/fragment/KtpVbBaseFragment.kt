package com.sbellanger.arch.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.sbellanger.arch.helper.timberConcreteClassLinkTag
import timber.log.Timber

abstract class KtpVbBaseFragment<VB : ViewBinding> : KtpBaseFragment() {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    protected val binding: VB
        get() = _binding!!

    private var _binding: VB? = null

    ///////////////////////////////////////////////////////////////////////////
    // LIFECYCLE
    ///////////////////////////////////////////////////////////////////////////

    @SuppressLint("MissingSuperCall")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.v(timberConcreteClassLinkTag)
        _binding = bindView(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC API
    ///////////////////////////////////////////////////////////////////////////

    abstract fun bindView(inflater: LayoutInflater, container: ViewGroup?): VB
}
