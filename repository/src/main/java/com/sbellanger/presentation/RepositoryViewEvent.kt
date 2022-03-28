package com.sbellanger.presentation

sealed class RepositoryViewEvent {
    object RepositoryAdded : RepositoryViewEvent()
    object RepositoryRemoved : RepositoryViewEvent()
}
