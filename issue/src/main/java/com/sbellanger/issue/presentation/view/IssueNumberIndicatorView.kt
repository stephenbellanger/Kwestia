package com.sbellanger.issue.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.sbellanger.issue.databinding.ViewIssueNumberIndicatorBinding

class IssueNumberIndicatorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    ///////////////////////////////////////////////////////////////////////////
    // CONFIGURATION
    ///////////////////////////////////////////////////////////////////////////

    private val binding: ViewIssueNumberIndicatorBinding =
        ViewIssueNumberIndicatorBinding
            .inflate(
                LayoutInflater.from(context),
                this@IssueNumberIndicatorView,
                false
            )

    ///////////////////////////////////////////////////////////////////////////
    // LIFECYCLE
    ///////////////////////////////////////////////////////////////////////////

    init {
        addView(binding.root)
    }

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC API
    ///////////////////////////////////////////////////////////////////////////

    fun setIssueNumber(number: Int) {
        binding.issueNumber.text = number.toString()
    }
}
