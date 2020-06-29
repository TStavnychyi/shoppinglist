package com.stavnychyy.shoppinglist.navigation

import com.stavnychyy.shoppinglist.ShoppingListId


interface AppNavigator {
    fun openAddShoppingListScreen()
    fun opeShoppingListDetailsScreen(shoppingListId: ShoppingListId, title: String, inReadMode: Boolean)
}
