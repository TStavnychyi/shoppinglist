package com.stavnychyy.shoppinglist.storage

import com.stavnychyy.shoppinglist.storage.shopping_list.ShoppingListDao


interface DatabaseProvider {
    fun createShoppingListDao(): ShoppingListDao
}
