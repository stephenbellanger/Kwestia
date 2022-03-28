package com.sbellanger.ui_kit.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.sbellanger.ui_kit.databinding.ViewNoResultBinding

class NoResultView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    ///////////////////////////////////////////////////////////////////////////
    // CONFIGURATION
    ///////////////////////////////////////////////////////////////////////////

    private val binding: ViewNoResultBinding =
        ViewNoResultBinding
            .inflate(
                LayoutInflater.from(context),
                this@NoResultView,
                false
            )

    ///////////////////////////////////////////////////////////////////////////
    // LIFECYCLE
    ///////////////////////////////////////////////////////////////////////////

    init {
        addView(binding.root)
    }
}
