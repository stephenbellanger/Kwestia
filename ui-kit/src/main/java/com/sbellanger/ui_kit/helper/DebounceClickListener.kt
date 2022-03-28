package com.sbellanger.ui_kit.helper

import android.os.SystemClock
import android.view.View

/**
 * A Throttled OnClickListener
 * Rejects clicks that are too close together in time.
 * This class is safe to use as an OnClickListener for multiple views, and will throttle each one separately.
 *
 *  * @param minimumIntervalMsec The minimum allowed time between clicks - any click sooner than this after a
 *  previous click will be rejected
 */

fun View.clickWithDebounce(debounceTime: Long = 500L, action: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action()

            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}
