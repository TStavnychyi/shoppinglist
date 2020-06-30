package com.stavnychyy.shoppinglist.shoppinglistdetails.view.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.stavnychyy.shoppinglist.common.adapter.DefaultListDiffUtilCallback
import com.stavnychyy.shoppinglist.common.extensions.inflateNoAttach
import com.stavnychyy.shoppinglist.common.extensions.layoutInflater
import com.stavnychyy.shoppinglist.shoppinglistdetails.R

class ShoppingListItemsAdapter(
  private val isInReadMode: Boolean
) : PagedListAdapter<ShoppingListItemViewEntity, ShoppingListItemsViewHolder>(
  DefaultListDiffUtilCallback()
) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListItemsViewHolder {
    return ShoppingListItemsViewHolder(
      isInReadMode,
      parent.layoutInflater().inflateNoAttach(R.layout.msla_shopping_list_details_adapter_item_layout, parent)
    )
  }

  override fun onBindViewHolder(holder: ShoppingListItemsViewHolder, position: Int) {
    getItem(position)?.let { holder.applyViewEntity(it) }
  }
}
