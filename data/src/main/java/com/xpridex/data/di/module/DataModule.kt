package com.xpridex.data.di.module

import com.xpridex.data.datasource.MovieDataSource
import com.xpridex.data.datasource.remote.MovieDataSourceRemote
import com.xpridex.data.repository.MovieRepositoryData
import com.xpridex.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Singleton
    @Provides
    fun provideDogMovieRepository(data: MovieRepositoryData): MovieRepository {
        return data
    }

    @Singleton
    @Provides
    fun provideMovieDataSource(dataSourceRemote: MovieDataSourceRemote): MovieDataSource {
        return dataSourceRemote
    }
}