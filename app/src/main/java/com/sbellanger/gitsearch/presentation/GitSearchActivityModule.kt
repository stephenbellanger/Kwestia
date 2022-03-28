package com.sbellanger.gitsearch.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import com.sbellanger.arch.navigation.IAppFragmentBuilder
import com.sbellanger.arch.navigation.IAppNavigation
import com.sbellanger.arch.network.IWsConfig
import com.sbellanger.arch.network.api.GithubWsConfig
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Module
@InstallIn(ActivityComponent::class)
abstract class GitSearchActivityModule {

    @Binds
    abstract fun bindIWsConfig(
        githubWsConfig: GithubWsConfig
    ): IWsConfig

    @Binds
    abstract fun bindIAppFragmentBuilder(
        gitSearchFragmentBuilder: GitSearchFragmentBuilder
    ): IAppFragmentBuilder

    @Binds
    abstract fun bindIAppNavigation(
        gitSearchNavigator: GitSearchNavigator
    ): IAppNavigation
}
