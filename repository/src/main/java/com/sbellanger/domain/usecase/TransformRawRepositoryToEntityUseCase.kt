package com.sbellanger.domain.usecase

import com.sbellanger.arch.local.model.Repository
import com.sbellanger.domain.model.RepositoryEntity
import javax.inject.Inject

class TransformRawRepositoryToEntityUseCase @Inject constructor() {

    //////////////////////////////////////////////////////////////////////////
    // PUBLIC API
    ///////////////////////////////////////////////////////////////////////////

    fun execute(
        raw: List<Repository>,
        favoriteRepositories: List<Repository>
    ): List<RepositoryEntity> {
        return raw.map {
            RepositoryEntity(
                id = it.id,
                name = it.name,
                description = it.description,
                isFavorite = favoriteRepositories.contains(it),
                language = it.language,
                openIssuesCount = it.openIssuesCount,
                repositoryName = it.repositoryName
            )
        }
    }
}
