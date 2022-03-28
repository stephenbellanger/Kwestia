package com.sbellanger.presentation

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
abstract class RepositoryFragmentModule {

    /*@Binds
    abstract fun bindIIRepositoryContractViewModel(
        repositoryViewModel: RepositoryViewModel
    ): IRepositoryContract.ViewModel*/

    @Binds
    abstract fun bindIRepositoryContractViewNavigation(
        repositoryNavigator: RepositoryNavigator
    ): IRepositoryContract.ViewNavigation
}
