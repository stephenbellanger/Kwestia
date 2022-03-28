package com.sbellanger.arch.rx

import io.reactivex.observers.DisposableObserver
import timber.log.Timber

open class RxLogSubscriber<T>(private val tag: String) : DisposableObserver<T>() {

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    override fun onStart() {
        super.onStart()
        Timber.tag("_$tag").i("onStart")
    }

    @SuppressWarnings("SwallowedException")
    override fun onNext(t: T) {
        try {
            if (t is List<*>) {
                Timber.tag("_$tag").v("onNext with : ${t.count()} items")
            } else {
                Timber.tag("_$tag").v("onNext with : $t")
            }
        } catch (e: IllegalStateException) {
            Timber.tag("_$tag").w("Object is no longer valid : ${e.localizedMessage}")
        }
    }

    override fun onError(e: Throwable) {
        Timber.tag("_$tag").e("onError with : $e")
    }

    override fun onComplete() {
        Timber.tag("_$tag").i("onComplete")
    }
}
