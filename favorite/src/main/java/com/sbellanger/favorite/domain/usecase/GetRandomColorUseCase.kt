package com.sbellanger.favorite.domain.usecase

import android.graphics.Color
import java.util.*
import javax.inject.Inject

class GetRandomColorUseCase @Inject constructor() {

    companion object {
        private const val ALPHA = 255
        private const val BOUND = 256
    }

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC API
    ///////////////////////////////////////////////////////////////////////////

    fun execute(): Int {
        val random = Random()
        return Color.argb(
            ALPHA,
            random.nextInt(BOUND),
            random.nextInt(BOUND),
            random.nextInt(BOUND)
        )
    }
}
