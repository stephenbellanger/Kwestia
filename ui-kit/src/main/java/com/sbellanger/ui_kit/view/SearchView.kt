package com.sbellanger.ui_kit.view

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.sbellanger.ui_kit.databinding.ViewSearchBinding

class SearchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), TextWatcher {

    ///////////////////////////////////////////////////////////////////////////
    // CONFIGURATION
    ///////////////////////////////////////////////////////////////////////////

    private val binding: ViewSearchBinding =
        ViewSearchBinding
            .inflate(
                LayoutInflater.from(context),
                this@SearchView,
                false
            )

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private var listener: IOnSearchListener? = null

    ///////////////////////////////////////////////////////////////////////////
    // LIFECYCLE
    ///////////////////////////////////////////////////////////////////////////

    init {
        with(binding) {
            addView(root)

            searchField.addTextChangedListener(this@SearchView)

            crossIcon.setOnClickListener {
                listener?.onClear()
                searchField.text.clear()
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC API
    ///////////////////////////////////////////////////////////////////////////

    interface IOnSearchListener {
        fun onClear()
        fun onTextChanged(text: String)
        fun afterTextChanged(text: String)
    }

    fun addSearchListener(listener: IOnSearchListener) {
        this.listener = listener
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        // Nothing
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        val currentText = binding.searchField.text.toString()
        listener?.onTextChanged(currentText)
    }

    override fun afterTextChanged(p0: Editable?) {
        val currentText = binding.searchField.text.toString()
        listener?.afterTextChanged(currentText)
    }
}
