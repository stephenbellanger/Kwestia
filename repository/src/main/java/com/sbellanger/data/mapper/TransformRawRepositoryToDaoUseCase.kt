package com.sbellanger.data.mapper

import com.sbellanger.arch.local.model.Repository
import com.sbellanger.arch.network.model.RawRepository
import javax.inject.Inject

class TransformRawRepositoryToDaoUseCase @Inject constructor() {

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC API
    ///////////////////////////////////////////////////////////////////////////

    fun execute(raw: RawRepository): List<Repository> {
        return raw.items.mapNotNull {
            val id = it.id
            val name = it.name
            val description = it.description
            val language = it.language
            val openIssuesCount = it.openIssuesCount
            val repositoryName = it.fullName
            if (id != null && name != null && description != null && language != null && openIssuesCount != null && repositoryName != null) {
                Repository(
                    id = id,
                    name = name,
                    description = description,
                    language = language,
                    openIssuesCount = openIssuesCount,
                    repositoryName = repositoryName
                )
            } else null
        }
    }
}
