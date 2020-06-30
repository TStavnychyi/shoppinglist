package com.stavnychyy.shoppinglist.domain

data class ShoppingListItem(
  val title: String,
  val notes: String,
  val isChecked: Boolean,
  val shoppingListId: ShoppingListId,
  val id: ShoppingListItemId? = null
)
