package com.stavnychyy.shoppinglist.model

import androidx.paging.DataSource
import com.stavnychyy.shoppinglist.ShoppingList
import com.stavnychyy.shoppinglist.shopping_list.ShoppingListDao
import javax.inject.Inject


class ShoppingListRepository @Inject constructor(private val shoppingListDao: ShoppingListDao) {

    fun getAllShoppingLists(): DataSource.Factory<Int, ShoppingList> {
        return shoppingListDao.getAllShoppingLists()
    }
}
