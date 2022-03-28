package com.sbellanger.gitsearch.presentation

import android.app.Application
import android.content.res.Resources
import androidx.fragment.app.FragmentActivity
import com.sbellanger.gitsearch.databinding.MainActivityBinding
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object GitSearchActivityProviderModule {

    @Provides
    fun provideMainActivityBinding(
        activity: FragmentActivity
    ): MainActivityBinding {
        return MainActivityBinding.inflate(activity.layoutInflater)
    }

    @Provides
    fun provideResources(
        application: Application
    ): Resources {
        return application.resources
    }
}
