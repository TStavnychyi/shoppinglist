package com.stavnychyy.shoppinglist.storage

import com.stavnychyy.shoppinglist.domain.ShoppingList
import com.stavnychyy.shoppinglist.domain.ShoppingListId
import com.stavnychyy.shoppinglist.domain.ShoppingListItem
import com.stavnychyy.shoppinglist.domain.ShoppingListItemId
import com.stavnychyy.shoppinglist.storage.shopping_list.DBShoppingList
import com.stavnychyy.shoppinglist.storage.shopping_list.DBShoppingListItem
import com.stavnychyy.shoppinglist.storage.shopping_list.DBShoppingListWithItems

internal fun DBShoppingListWithItems.toDomain() = ShoppingList(
  shoppingList.name,
  shoppingList.purchaseDate,
  items.map { it.toDomain() },
  shoppingList.isArchived,
  shoppingList.id?.let { ShoppingListId(it) }
)

internal fun DBShoppingList.toDomain() = ShoppingList(
  name,
  purchaseDate,
  emptyList(),
  isArchived,
  id?.let { ShoppingListId(it) }
)

internal fun DBShoppingListItem.toDomain() = ShoppingListItem(
  title,
  notes,
  isChecked,
  ShoppingListId(shoppingListId),
  id?.let { ShoppingListItemId(it) }
)
