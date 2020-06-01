package com.xpridex.movies.presentation.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    Picasso
        .get()
        .load(url)
        .fit()
        .centerCrop()
        .into(this)
}

