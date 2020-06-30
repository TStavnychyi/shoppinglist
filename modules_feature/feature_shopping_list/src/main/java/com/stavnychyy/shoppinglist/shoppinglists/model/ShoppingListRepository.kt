package com.stavnychyy.shoppinglist.shoppinglists.model

import androidx.paging.DataSource
import com.stavnychyy.shoppinglist.domain.ShoppingList
import com.stavnychyy.shoppinglist.storage.shopping_list.ShoppingListDao
import io.reactivex.Completable
import javax.inject.Inject

class ShoppingListRepository @Inject constructor(private val shoppingListDao: ShoppingListDao) {

    fun getAllShoppingLists(): DataSource.Factory<Int, ShoppingList> {
        return shoppingListDao.getAllShoppingLists()
    }

    fun deleteShoppingListWithItems(shoppingList: ShoppingList): Completable {
        return shoppingListDao.deleteShoppingListWithItems(shoppingList)
    }

    fun updateShoppingList(shoppingList: ShoppingList): Completable {
        return shoppingListDao.updateShoppingList(shoppingList)
    }
}