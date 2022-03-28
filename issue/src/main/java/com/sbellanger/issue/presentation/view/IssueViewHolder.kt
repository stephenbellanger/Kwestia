package com.sbellanger.issue.presentation.view

import androidx.recyclerview.widget.RecyclerView
import com.sbellanger.issue.databinding.ViewIssueBinding
import com.sbellanger.issue.domain.model.IssueEntity

class IssueViewHolder(
    private val itemBinding: ViewIssueBinding,
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(issue: IssueEntity) {
        with(itemBinding) {
            name.text = issue.name
            issueNumber.text = issue.number
            openedBy.text = issue.openedBy
        }
    }
}
