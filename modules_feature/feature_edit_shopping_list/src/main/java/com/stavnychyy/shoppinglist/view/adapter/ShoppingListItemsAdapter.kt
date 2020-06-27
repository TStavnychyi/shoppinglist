package com.stavnychyy.shoppinglist.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.stavnychyy.shoppinglist.R
import com.stavnychyy.shoppinglist.adapter.DefaultDiffUtilCallback
import com.stavnychyy.shoppinglist.extensions.inflateNoAttach
import com.stavnychyy.shoppinglist.extensions.layoutInflater


class ShoppingListItemsAdapter : RecyclerView.Adapter<ShoppingListItemsViewHolder>() {

    private var items: List<ShoppingListItemViewEntity> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListItemsViewHolder {
        return ShoppingListItemsViewHolder(
            parent.layoutInflater().inflateNoAttach(R.layout.msla_edit_shopping_list_adapter_item_layout, parent)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ShoppingListItemsViewHolder, position: Int) {
        holder.applyViewEntity(items[position])
    }

    fun setItems(newItems: List<ShoppingListItemViewEntity>) {
        val diffUtilResult = DiffUtil.calculateDiff(DefaultDiffUtilCallback(items, newItems))
        items = newItems
        diffUtilResult.dispatchUpdatesTo(this)
    }
}
