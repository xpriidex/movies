package com.xpridex.movies.di.module

import com.xpridex.movies.presentation.navigation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(
        modules = [
            FragmentProvider::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity
}
