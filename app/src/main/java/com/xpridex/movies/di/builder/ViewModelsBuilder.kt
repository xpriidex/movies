package com.xpridex.movies.di.builder

import androidx.lifecycle.ViewModel
import com.xpridex.movies.di.ViewModelKey
import com.xpridex.movies.presentation.movielist.viewmodel.MovieListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsBuilder {

   @Binds
   @IntoMap
   @ViewModelKey(MovieListViewModel::class)
   abstract fun bindMovieListViewModel(authViewModel: MovieListViewModel): ViewModel
}