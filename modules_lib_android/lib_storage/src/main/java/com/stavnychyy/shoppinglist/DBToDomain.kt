package com.stavnychyy.shoppinglist

import com.stavnychyy.shoppinglist.shopping_list.DBShoppingList
import com.stavnychyy.shoppinglist.shopping_list.DBShoppingListItem
import com.stavnychyy.shoppinglist.shopping_list.DBShoppingListWithItems


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
    ShoppingListId(shoppingListId)
)
