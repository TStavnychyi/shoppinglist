package com.stavnychyy.shoppinglist.view.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.stavnychyy.shoppinglist.R
import com.stavnychyy.shoppinglist.adapter.DefaultListDiffUtilCallback
import com.stavnychyy.shoppinglist.extensions.inflateNoAttach
import com.stavnychyy.shoppinglist.extensions.layoutInflater


class ShoppingListItemsAdapter(
    private val isInReadMode: Boolean
) : PagedListAdapter<ShoppingListItemViewEntity, ShoppingListItemsViewHolder>(
    DefaultListDiffUtilCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListItemsViewHolder {
        return ShoppingListItemsViewHolder(
            isInReadMode,
            parent
                .layoutInflater()
                .inflateNoAttach(R.layout.msla_edit_shopping_list_adapter_item_layout, parent)
        )
    }

    override fun onBindViewHolder(holder: ShoppingListItemsViewHolder, position: Int) {
        getItem(position)?.let { holder.applyViewEntity(it) }
    }
}
