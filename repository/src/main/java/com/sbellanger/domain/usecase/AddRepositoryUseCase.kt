package com.sbellanger.domain.usecase

import com.sbellanger.data.IRepositoryRepository
import com.sbellanger.domain.model.RepositoryEntity
import io.reactivex.Completable
import javax.inject.Inject

class AddRepositoryUseCase @Inject constructor() {

    ///////////////////////////////////////////////////////////////////////////
    // DEPENDENCY
    ///////////////////////////////////////////////////////////////////////////

    @Inject
    lateinit var repository: IRepositoryRepository

    @Inject
    lateinit var transformRepositoryEntityToDaoUseCase: TransformRepositoryEntityToDaoUseCase

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC API
    ///////////////////////////////////////////////////////////////////////////

    fun execute(repository: RepositoryEntity): Completable {
        return this.repository
            .addRepository(
                transformRepositoryEntityToDaoUseCase.execute(repository)
            )
    }
}
