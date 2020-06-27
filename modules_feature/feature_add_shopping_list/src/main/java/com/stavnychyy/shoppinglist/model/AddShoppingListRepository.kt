package com.stavnychyy.shoppinglist.model

import com.stavnychyy.shoppinglist.ShoppingList
import com.stavnychyy.shoppinglist.shopping_list.ShoppingListDao
import io.reactivex.Completable
import javax.inject.Inject


class AddShoppingListRepository @Inject constructor(private val shoppingListDao: ShoppingListDao) {

    fun saveShoppingList(shoppingList: ShoppingList): Completable {
        return shoppingListDao.saveShoppingList(shoppingList)
    }
}
