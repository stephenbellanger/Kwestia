package com.sbellanger.issue.domain.usecase

import com.sbellanger.arch.local.model.Issue
import com.sbellanger.issue.domain.model.IssueEntity
import javax.inject.Inject

class TransformRawIssueToEntityUseCase @Inject constructor() {

    companion object {
        private const val HASHTAG = "#"
        private const val OPENED_BY_TEXT = "Opened by"
    }

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC API
    ///////////////////////////////////////////////////////////////////////////

    fun execute(raw: List<Issue>): List<IssueEntity> {
        return raw.map {
            IssueEntity(
                name = it.name,
                number = "$HASHTAG${it.number}",
                description = it.description,
                openedBy = "$OPENED_BY_TEXT ${it.openedBy}"
            )
        }
    }
}
