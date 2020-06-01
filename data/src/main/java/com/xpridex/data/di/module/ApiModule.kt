package com.xpridex.data.di.module

import com.xpridex.data.api.MovieApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit

@Module
class ApiModule {
    @Reusable
    @Provides
    fun provideDogApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }
}