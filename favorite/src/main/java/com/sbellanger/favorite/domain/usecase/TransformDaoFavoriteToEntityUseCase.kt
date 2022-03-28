package com.sbellanger.favorite.domain.usecase

import com.sbellanger.arch.local.model.Repository
import com.sbellanger.favorite.domain.model.FavoriteEntity
import javax.inject.Inject

class TransformDaoFavoriteToEntityUseCase @Inject constructor() {

    ///////////////////////////////////////////////////////////////////////////
    // DEPENDENCY
    ///////////////////////////////////////////////////////////////////////////

    @Inject
    lateinit var getRandomColorUseCase: GetRandomColorUseCase

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC API
    ///////////////////////////////////////////////////////////////////////////

    fun execute(raw: List<Repository>): List<FavoriteEntity> {
        return raw.map {
            FavoriteEntity(
                id = it.id,
                name = it.name,
                description = it.description,
                language = it.language,
                openedIssues = it.openIssuesCount,
                color = getRandomColorUseCase.execute(),
                repositoryName = it.repositoryName
            )
        }
    }
}
