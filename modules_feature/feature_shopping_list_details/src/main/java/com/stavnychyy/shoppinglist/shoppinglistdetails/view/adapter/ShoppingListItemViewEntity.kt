package com.stavnychyy.shoppinglist.shoppinglistdetails.view.adapter

import com.stavnychyy.shoppinglist.domain.ShoppingListItem

data class ShoppingListItemViewEntity(
  val title: String,
  val notes: String,
  val isChecked: Boolean,
  val shoppingListItem: ShoppingListItem,
  val onItemDeleteClickListener: (item: ShoppingListItem) -> Unit,
  val onItemCheckedChangeListener: (item: ShoppingListItem) -> Unit
) {
  companion object {
    fun create(
      shoppingListItem: ShoppingListItem,
      onItemDeleteClickListener: (item: ShoppingListItem) -> Unit,
      onItemCheckedChangeListener: (item: ShoppingListItem) -> Unit
    ): ShoppingListItemViewEntity {
      return with(shoppingListItem) {
        ShoppingListItemViewEntity(
          title,
          notes,
          isChecked,
          shoppingListItem,
          onItemDeleteClickListener = onItemDeleteClickListener,
          onItemCheckedChangeListener = onItemCheckedChangeListener
        )
      }
    }
  }
}
