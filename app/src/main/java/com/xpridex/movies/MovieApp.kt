package com.xpridex.movies

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.xpridex.movies.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class MovieApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    companion object {
        private lateinit var instance: MovieApp

        fun getAppContext(): Context = instance.applicationContext

    }


}