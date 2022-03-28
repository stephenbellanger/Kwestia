package com.sbellanger.arch.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.sbellanger.arch.helper.timberConcreteClassLinkTag
import timber.log.Timber

abstract class BaseFragment : Fragment() {

    ///////////////////////////////////////////////////////////////////////////
    // LIFECYCLE
    ///////////////////////////////////////////////////////////////////////////

    @CallSuper
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Timber.v(timberConcreteClassLinkTag)
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.v(timberConcreteClassLinkTag)
    }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.v(timberConcreteClassLinkTag)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.v(timberConcreteClassLinkTag)
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        Timber.v(timberConcreteClassLinkTag)
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
    override fun onStop() {
        Timber.v(timberConcreteClassLinkTag)
        super.onStop()
    }

    @CallSuper
    override fun onDestroyView() {
        Timber.v(timberConcreteClassLinkTag)
        super.onDestroyView()
    }

    @CallSuper
    override fun onDestroy() {
        Timber.v(timberConcreteClassLinkTag)
        super.onDestroy()
    }

    @CallSuper
    override fun onDetach() {
        super.onDetach()
        Timber.v(timberConcreteClassLinkTag)
    }
}
