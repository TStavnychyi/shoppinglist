package com.stavnychyy.shoppinglist.navigation

import com.stavnychyy.shoppinglist.ShoppingListId


interface AppNavigator {
    fun openAddShoppingListScreen()
    fun openEditShoppingListScreen(shoppingListId: ShoppingListId)
}
