package com.sbellanger.presentation

import com.sbellanger.domain.model.RepositoryEntity

sealed class RepositoryViewState {
    data class Loaded(
        val repositories: List<RepositoryEntity>
    ) : RepositoryViewState()

    object Loading : RepositoryViewState()
    object Error : RepositoryViewState()
    object NoResult : RepositoryViewState()
    object Init : RepositoryViewState()
}
