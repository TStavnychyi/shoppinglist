package com.stavnychyy.shoppinglist.view.adapter

import com.stavnychyy.shoppinglist.ShoppingListItem


data class ShoppingListItemViewEntity(
    val title: String,
    val notes: String,
    val isChecked: Boolean
) {
    companion object {
        fun create(shoppingListItem: ShoppingListItem): ShoppingListItemViewEntity {
            return with(shoppingListItem) {
                ShoppingListItemViewEntity(title, notes, isChecked)
            }
        }
    }
}
