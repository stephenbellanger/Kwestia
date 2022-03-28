package com.sbellanger.domain.usecase

import com.sbellanger.data.IRepositoryRepository
import com.sbellanger.domain.model.RepositoryEntity
import io.reactivex.Observable
import javax.inject.Inject

class GetRepositoriesUseCase @Inject constructor() {

    ///////////////////////////////////////////////////////////////////////////
    // DEPENDENCY
    ///////////////////////////////////////////////////////////////////////////

    @Inject
    lateinit var repository: IRepositoryRepository

    @Inject
    lateinit var transformRawRepositoryToEntityUseCase: TransformRawRepositoryToEntityUseCase

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC API
    ///////////////////////////////////////////////////////////////////////////

    fun execute(name: String): Observable<List<RepositoryEntity>> {
        return Observable.combineLatest(
            repository.requestRepositories(name),
            repository.getRepositories()
        ) { result, favorite ->
            transformRawRepositoryToEntityUseCase.execute(result, favorite)
        }
    }
}
