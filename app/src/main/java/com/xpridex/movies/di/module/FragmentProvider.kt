package com.xpridex.movies.di.module

import com.xpridex.movies.presentation.moviedetail.view.MovieDetailFragment
import com.xpridex.movies.presentation.movielist.view.MovieListFragment
import com.xpridex.movies.presentation.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentProvider {

    @ContributesAndroidInjector
    abstract fun provideSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun provideMovieListFragment(): MovieListFragment

    @ContributesAndroidInjector
    abstract fun provideMovieDetailFragment(): MovieDetailFragment

}