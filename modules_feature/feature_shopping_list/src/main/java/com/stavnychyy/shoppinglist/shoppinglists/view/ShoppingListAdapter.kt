package com.stavnychyy.shoppinglist.shoppinglists.view

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.stavnychyy.shoppinglist.common.adapter.DefaultListDiffUtilCallback
import com.stavnychyy.shoppinglist.common.extensions.inflateNoAttach
import com.stavnychyy.shoppinglist.common.extensions.layoutInflater
import com.stavnychyy.shoppinglist.shoppinglists.R
import com.stavnychyy.shoppinglist.shoppinglists.viewmodel.ShoppingListItemViewEntity


class ShoppingListAdapter :
    PagedListAdapter<ShoppingListItemViewEntity, ShoppingListItemViewHolder>(
        DefaultListDiffUtilCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListItemViewHolder {
        val itemView = parent.layoutInflater().inflateNoAttach(
            R.layout.msla_shopping_list_adapter_item,
            parent
        )
        return ShoppingListItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ShoppingListItemViewHolder, position: Int) {
        getItem(position)?.let { holder.applyViewEntity(it) }
    }
}
