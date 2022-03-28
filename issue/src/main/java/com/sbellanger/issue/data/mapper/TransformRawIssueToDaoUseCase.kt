package com.sbellanger.issue.data.mapper

import com.sbellanger.arch.local.model.Issue
import com.sbellanger.arch.network.model.RawIssue
import javax.inject.Inject

class TransformRawIssueToDaoUseCase @Inject constructor() {

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC API
    ///////////////////////////////////////////////////////////////////////////

    fun execute(raw: List<RawIssue>): List<Issue> {
        return raw.mapNotNull {
            val id = it.id
            val title = it.title
            val number = it.number
            val description = it.body
            val openedBy = it.user?.login
            if (id != null && title != null && number != null && description != null && openedBy != null) {
                Issue(
                    id = id,
                    name = title,
                    number = number,
                    description = description,
                    openedBy = openedBy
                )
            } else null
        }
    }
}
