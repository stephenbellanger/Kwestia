package com.sbellanger.gitsearch.presentation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.sbellanger.arch.navigation.IAppFragmentBuilder
import com.sbellanger.arch.navigation.IAppNavigation
import com.sbellanger.gitsearch.databinding.MainActivityBinding
import toothpick.config.Module
import toothpick.ktp.binding.bind

class GitSearchActivityModule(activity: AppCompatActivity) : Module() {
    init {
        bind(FragmentActivity::class.java).toInstance(activity)
        bind<MainActivityBinding>().toInstance(MainActivityBinding.inflate(activity.layoutInflater))
        bind<IAppFragmentBuilder>().toClass<GitSearchFragmentBuilder>()
        bind<IAppNavigation>().toClass<GitSearchNavigator>()
    }
}
