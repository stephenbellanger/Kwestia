package com.sbellanger.ui_kit.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.sbellanger.ui_kit.databinding.ViewLoaderBinding

class LoaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    ///////////////////////////////////////////////////////////////////////////
    // CONFIGURATION
    ///////////////////////////////////////////////////////////////////////////

    private val binding: ViewLoaderBinding =
        ViewLoaderBinding
            .inflate(
                LayoutInflater.from(context),
                this@LoaderView,
                false
            )

    ///////////////////////////////////////////////////////////////////////////
    // LIFECYCLE
    ///////////////////////////////////////////////////////////////////////////

    init {
        addView(binding.root)
    }
}
