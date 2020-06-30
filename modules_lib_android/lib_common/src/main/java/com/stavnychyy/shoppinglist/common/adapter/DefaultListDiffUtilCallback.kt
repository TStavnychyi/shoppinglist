package com.stavnychyy.shoppinglist.common.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class DefaultListDiffUtilCallback<ItemType : Any> : DiffUtil.ItemCallback<ItemType>() {
    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: ItemType, newItem: ItemType): Boolean = newItem == oldItem
    override fun areItemsTheSame(oldItem: ItemType, newItem: ItemType): Boolean = newItem == oldItem
}

class DefaultDiffUtilCallback<ItemType : Any>(
    private val oldList: List<ItemType>,
    private val newList: List<ItemType>
) : AbstractDiffUtilCallback<ItemType>(oldList, newList) {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldItemPosition == newItemPosition

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}

abstract class AbstractDiffUtilCallback<ItemType : Any>(
    private val oldList: List<ItemType>,
    private val newList: List<ItemType>
) : DiffUtil.Callback() {

    final override fun getOldListSize() = oldList.size

    final override fun getNewListSize() = newList.size
}
