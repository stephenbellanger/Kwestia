package com.sbellanger.domain.usecase

import com.sbellanger.presentation.RepositoryViewState
import io.reactivex.Observable
import javax.inject.Inject

class GetRepositoryViewStateUseCase @Inject constructor() {

    ///////////////////////////////////////////////////////////////////////////
    // DEPENDENCY
    ///////////////////////////////////////////////////////////////////////////

    @Inject
    lateinit var getRepositoriesUseCase: GetRepositoriesUseCase

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC API
    ///////////////////////////////////////////////////////////////////////////

    fun execute(name: String): Observable<RepositoryViewState> {
        return getRepositoriesUseCase
            .execute(name)
            .map { repositories ->
                if (repositories.isNotEmpty()) {
                    RepositoryViewState.Loaded(repositories)
                } else {
                    RepositoryViewState.NoResult
                }
            }
            .onErrorReturnItem(RepositoryViewState.Error)
    }
}
