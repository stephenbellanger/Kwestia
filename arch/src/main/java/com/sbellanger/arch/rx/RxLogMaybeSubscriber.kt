package com.sbellanger.arch.rx

import io.reactivex.observers.DisposableMaybeObserver
import timber.log.Timber

class RxLogMaybeSubscriber<T>(private val tag: String) : DisposableMaybeObserver<T>() {

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    override fun onStart() {
        super.onStart()
        Timber.tag("_$tag").i("onStart")
    }

    @SuppressWarnings("SwallowedException")
    override fun onSuccess(t: T) {
        try {
            Timber.tag("_$tag").i("onSuccess with : $t")
        } catch (e: IllegalStateException) {
            Timber.tag("_$tag").w("Object is no longer valid : ${e.localizedMessage}")
        }
    }

    override fun onError(e: Throwable) {
        Timber.tag("_$tag").e("onError with : $e")
    }

    override fun onComplete() {
        Timber.tag("_$tag").v("onComplete")
    }
}
