package com.xpridex.movies.presentation.movielist.view

import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.xpridex.movies.R
import com.xpridex.movies.databinding.RvMovieItemBinding
import com.xpridex.movies.presentation.common.BaseAdapter
import com.xpridex.movies.presentation.common.BaseViewHolder
import com.xpridex.movies.presentation.movielist.model.Movie

class MoviesAdapter(private val onMovieTapped: (Movie) -> Unit) : BaseAdapter<Movie>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Movie> {
        return MoviesViewHolder(inflate(parent, R.layout.rv_movie_item))
    }

    inner class MoviesViewHolder(itemView: View) : BaseViewHolder<Movie>(itemView) {
        private val binding = RvMovieItemBinding.bind(itemView)

        init {
            binding.root.setOnClickListener {
                val notification = getItem(adapterPosition)
                notification?.let {
                    onMovieTapped(it)
                }
            }
        }

        override fun bind(item: Movie) {
            Picasso.get().load(item.posterPath)
                .placeholder(R.drawable.ic_launcher_background)
                .fit().centerCrop()
                .into(binding.ivMovie)

            binding.tvTitle.text = item.title
        }
    }

}
