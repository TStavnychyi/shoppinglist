package com.stavnychyy.shoppinglist.shoppinglists.viewmodel

import com.stavnychyy.shoppinglist.domain.ShoppingList

data class ShoppingListItemViewEntity(
  val title: String,
  val createdDate: String,
  val shoppingList: ShoppingList,
  val onDeleteItemClickListener: (id: ShoppingList) -> Unit,
  val onArchiveItemClickListener: (id: ShoppingList) -> Unit,
  val onItemClickListener: () -> Unit
)
