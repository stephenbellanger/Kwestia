package com.sbellanger.gitsearch.presentation

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.fragment.app.FragmentActivity
import com.sbellanger.arch.navigation.IAppFragmentBuilder
import com.sbellanger.arch.navigation.IAppNavigation
import com.sbellanger.gitsearch.databinding.MainActivityBinding
import toothpick.config.Module
import toothpick.ktp.binding.bind

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
class GitSearchActivityModule(activity: AppCompatActivity) : Module() {
    init {
        bind(FragmentActivity::class.java).toInstance(activity)
        bind<MainActivityBinding>().toInstance(MainActivityBinding.inflate(activity.layoutInflater))
        bind<IAppFragmentBuilder>().toClass<GitSearchFragmentBuilder>()
        bind<IAppNavigation>().toClass<GitSearchNavigator>()
    }
}
