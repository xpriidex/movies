package com.xpridex.movies.presentation.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun ViewGroup.inflate(layoutResId: Int, attachRoot: Boolean): View {
    val inflater = LayoutInflater.from(context)
    return inflater.inflate(layoutResId, this, attachRoot)
}

fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()


fun RecyclerView.lastCompletelyVisibleItemPosition(visibleItem: () -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val lastCompletelyVisibleItemPosition =
                (layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
            if (lastCompletelyVisibleItemPosition + 5 >= (layoutManager as LinearLayoutManager).itemCount) {
                visibleItem.invoke()
            }
        }
    })
}
