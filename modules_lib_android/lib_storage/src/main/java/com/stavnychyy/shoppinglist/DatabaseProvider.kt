package com.stavnychyy.shoppinglist

import com.stavnychyy.shoppinglist.shopping_list.ShoppingListDao


interface DatabaseProvider {
    fun createShoppingListDao(): ShoppingListDao
}
