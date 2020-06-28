package com.stavnychyy.shoppinglist.viewmodel

import com.stavnychyy.shoppinglist.ShoppingList
import com.stavnychyy.shoppinglist.ShoppingListId

data class ShoppingListItemViewEntity(
  val title: String,
  val createdDate: String,
  val status: String,
  val shoppingList: ShoppingList,
  val onDeleteItemClickListener: (id: ShoppingList) -> Unit,
  val onArchiveItemClickListener: (id: ShoppingList) -> Unit,
  val onItemClickListener: () -> Unit
)
