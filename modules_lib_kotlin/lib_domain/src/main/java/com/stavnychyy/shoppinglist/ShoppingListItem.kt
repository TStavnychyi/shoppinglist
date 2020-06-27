package com.stavnychyy.shoppinglist


data class ShoppingListItem(
    val title: String,
    val notes: String,
    val isChecked: Boolean,
    val shoppingListId: ShoppingListId
)
