package com.xpridex.movies.di.module

import android.content.Context
import com.xpridex.movies.MovieApp
import com.xpridex.movies.di.builder.ViewModelFactoryBuilder
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [ViewModelFactoryBuilder::class])
abstract class ApplicationModule {
    @Binds
    @Singleton
    abstract fun bindContext(application: MovieApp): Context
}