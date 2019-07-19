package com.example.mppapp.util


abstract class PageableAdapter<T>(
    private var list: MutableList<T>
) : BaseAdapter<T>(list) {

    protected var isLoaderVisible = false

    open fun addLoader() {
        isLoaderVisible = true
        list.add(list[list.size - 1])
        notifyItemInserted(list.size - 1)
    }

    open fun addItems(list: MutableList<T>) {
        this.list.addAll(list)
        notifyItemRangeInserted(this.list.size, list.size)
    }

    open fun removeLoader() {
        isLoaderVisible = false
        list.removeAt(list.size - 1)
        notifyItemRemoved(list.size)
    }

    open fun updateDataSet(list: MutableList<T>) {
        this.list = list
        notifyDataSetChanged()
    }
}