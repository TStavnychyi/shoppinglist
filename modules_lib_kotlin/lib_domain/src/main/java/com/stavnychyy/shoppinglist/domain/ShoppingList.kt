package com.stavnychyy.shoppinglist.domain

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

  fun getCheckedItemsCount(): String {
    val checkedShoppingListItems = items.count { it.isChecked }
    return "$checkedShoppingListItems/${items.size}"
  }
}
