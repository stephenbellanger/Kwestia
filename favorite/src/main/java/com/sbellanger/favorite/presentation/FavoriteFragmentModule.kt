package com.sbellanger.favorite.presentation

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import toothpick.config.Module
import toothpick.ktp.binding.bind

class FavoriteFragmentModule(fragment: Fragment) : Module() {
    init {
        bind<IFavoriteContract.ViewModel>()
            .toInstance(ViewModelProvider(fragment)[FavoriteViewModel::class.java])
        bind<IFavoriteContract.ViewNavigation>()
            .toClass<FavoriteNavigator>()
    }
}
