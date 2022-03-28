package com.sbellanger.arch.viewmodel

import android.app.Application
import androidx.annotation.CallSuper
import androidx.lifecycle.AndroidViewModel
import com.sbellanger.arch.helper.timberConcreteClassLinkTag
import com.sbellanger.arch.rx.*
import io.reactivex.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private val compositeDisposable = CompositeDisposable()

    ///////////////////////////////////////////////////////////////////////////
    // LIFECYCLE
    ///////////////////////////////////////////////////////////////////////////

    @CallSuper
    protected open fun onCreated() {
        Timber.v(timberConcreteClassLinkTag)
    }

    @CallSuper
    override fun onCleared() {
        Timber.v(timberConcreteClassLinkTag)
        super.onCleared()
    }

    ///////////////////////////////////////////////////////////////////////////
    // RX DISPOSITION
    ///////////////////////////////////////////////////////////////////////////

    private fun Disposable.bindToDisposable() {
        compositeDisposable.add(this)
    }

    protected fun <T> Single<T>.bindSubAndLog(tag: String) {
        return this
            .subscribeWith(RxLogSingleSubscriber(tag))
            .bindToDisposable()
    }

    protected fun <T> Maybe<T>.bindSubAndLog(tag: String) {
        return this
            .subscribeWith(RxLogMaybeSubscriber(tag))
            .bindToDisposable()
    }

    protected fun <T> Observable<T>.bindSubAndLog(tag: String) {
        return this
            .subscribeWith(RxLogSubscriber(tag))
            .bindToDisposable()
    }

    protected fun <T> Flowable<T>.bindSubAndLog(tag: String) {
        return this
            .subscribeWith(RxLogFlowableSubscriber(tag))
            .bindToDisposable()
    }

    protected fun Completable.bindSubAndLog(tag: String) {
        return this
            .subscribeWith(RxLogCompletableSubscriber(tag))
            .bindToDisposable()
    }
}
