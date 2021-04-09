package com.amrabdelhameed.photoweatherapp.presentation.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T>(
    val items: MutableList<T>,
    val itemListener: BaseItemListener<T>?
) : RecyclerView.Adapter<BaseViewHolder>() {
    constructor(items: MutableList<T>) : this(items, null)

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun addItem(item: T?) {
        item?.let { this.items.add(0, it) }
        notifyDataSetChanged()
    }

    fun addItems(items: List<T>?) {
        items?.let { this.items.addAll(it) }
        notifyDataSetChanged()
    }

    fun clearItems() {
        items.clear()
    }
}