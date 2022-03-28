package com.sbellanger.domain.usecase

import com.sbellanger.arch.local.model.Repository
import com.sbellanger.domain.model.RepositoryEntity
import javax.inject.Inject

class TransformRepositoryEntityToDaoUseCase @Inject constructor() {

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC API
    ///////////////////////////////////////////////////////////////////////////

    fun execute(raw: RepositoryEntity): Repository {
        return Repository(
            id = raw.id,
            name = raw.name,
            description = raw.description,
            language = raw.language,
            openIssuesCount = raw.openIssuesCount,
            repositoryName = raw.repositoryName
        )
    }
}
