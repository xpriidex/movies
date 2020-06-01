package com.xpridex.movies.presentation.common

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView


abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    open fun bind(item: T) {}

    protected fun getColor(id: Int): Int = ContextCompat.getColor(itemView.context, id)

    protected fun getString(id: Int): String? = itemView.context.getString(id)

    protected fun getString(id: Int, vararg formatArgs: Any): String? = itemView.context.getString(id, *formatArgs)

    protected fun getDrawable(id: Int): Drawable? = ContextCompat.getDrawable(itemView.context, id)

    val context: Context get() = itemView.context
}

abstract class BaseAdapter<ITEM_TYPE> : RecyclerView.Adapter<BaseViewHolder<ITEM_TYPE>>() {

    internal var items = ArrayList<ITEM_TYPE>()

    override fun getItemCount(): Int = items.size
    protected fun inflate(parent: ViewGroup, id: Int): View {
        return LayoutInflater.from(parent.context).inflate(id, parent, false)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ITEM_TYPE>, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    fun getItem(position: Int): ITEM_TYPE? {
        return if (!items.isNullOrEmpty() && items.size > position && position >= 0) items[position] else null
    }

    fun set(index: Int, item: ITEM_TYPE) {
        items[index] = item
        notifyItemChanged(index)
    }

    fun remove(position: Int) {
        if (position >= 0) {
            items.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun addAll(newList: List<ITEM_TYPE>) {
        val lastIndex = itemCount
        items.addAll(newList)
        notifyItemRangeInserted(lastIndex, newList.size)
    }

    fun getItems(): ArrayList<ITEM_TYPE> {
        return items
    }
}
