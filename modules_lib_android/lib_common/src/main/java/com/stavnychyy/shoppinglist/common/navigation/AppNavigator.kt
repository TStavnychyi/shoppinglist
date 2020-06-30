package com.stavnychyy.shoppinglist.common.navigation

import com.stavnychyy.shoppinglist.domain.ShoppingListId


interface AppNavigator {
    fun openAddShoppingListScreen()
    fun opeShoppingListDetailsScreen(shoppingListId: ShoppingListId, title: String, inReadMode: Boolean)
}
