package com.sbellanger.issue.presentation.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sbellanger.issue.databinding.ViewIssueBinding
import com.sbellanger.issue.domain.model.IssueEntity
import javax.inject.Inject

class IssueAdapter @Inject constructor() : RecyclerView.Adapter<IssueViewHolder>() {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private val issues: MutableList<IssueEntity> = mutableListOf()

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueViewHolder {
        val itemBinding = ViewIssueBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return IssueViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        holder.bind(issues[position])
    }

    override fun getItemCount(): Int {
        return issues.size
    }

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC API
    ///////////////////////////////////////////////////////////////////////////

    @SuppressLint("NotifyDataSetChanged")
    fun setData(issues: List<IssueEntity>) {
        this.issues.apply {
            clear()
            addAll(issues)
        }
        notifyDataSetChanged()
    }

    fun clear() {
        issues.clear()
        notifyDataSetChanged()
    }
}
