package com.sbellanger.presentation

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import toothpick.config.Module
import toothpick.ktp.binding.bind

class RepositoryFragmentModule(fragment: Fragment) : Module() {
    init {
        bind<IRepositoryContract.ViewModel>()
            .toInstance(ViewModelProvider(fragment)[RepositoryViewModel::class.java])
        bind<IRepositoryContract.ViewNavigation>()
            .toClass<RepositoryNavigator>()
    }
}
