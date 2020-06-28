package com.stavnychyy.shoppinglist

import org.threeten.bp.LocalDate


data class ShoppingList(
    val name: String,
    val purchaseDate: LocalDate,
    val items: List<ShoppingListItem> = emptyList(),
    val isArchived: Boolean = false,
    val id: ShoppingListId? = null
) {
    fun changeArchiveStatus(isArchived: Boolean): ShoppingList {
        return this.copy(isArchived = isArchived)
    }
}
