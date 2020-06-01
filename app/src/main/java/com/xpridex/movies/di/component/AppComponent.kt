package com.xpridex.movies.di.component

import com.xpridex.data.di.module.ApiModule
import com.xpridex.data.di.module.DataModule
import com.xpridex.data.di.module.NetworkModule
import com.xpridex.movies.MovieApp
import com.xpridex.movies.di.module.ActivityModule
import com.xpridex.movies.di.module.ApplicationModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        ActivityModule::class,
        NetworkModule::class,
        DataModule::class,
        ApiModule::class
    ]
)
interface AppComponent : AndroidInjector<MovieApp> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MovieApp>()
}