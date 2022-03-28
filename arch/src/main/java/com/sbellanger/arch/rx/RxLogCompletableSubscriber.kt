package com.sbellanger.arch.rx

import io.reactivex.observers.DisposableCompletableObserver
import timber.log.Timber

class RxLogCompletableSubscriber(private val tag: String) : DisposableCompletableObserver() {

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    override fun onStart() {
        super.onStart()
        Timber.tag("_$tag").i("onStart")
    }

    override fun onError(e: Throwable) {
        Timber.tag("_$tag").e("onError with : $e")
    }

    override fun onComplete() {
        try {
            Timber.tag("_$tag").v("onComplete")
        } catch (e: IllegalStateException) {
            Timber.tag("_$tag").w("Object is no longer valid : ${e.localizedMessage}")
        }
    }
}
