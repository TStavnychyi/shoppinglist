package com.stavnychyy.shoppinglist.addshoppinglist.model

import com.stavnychyy.shoppinglist.domain.ShoppingList
import com.stavnychyy.shoppinglist.storage.shopping_list.ShoppingListDao
import io.reactivex.Completable
import javax.inject.Inject


class AddShoppingListRepository @Inject constructor(private val shoppingListDao: ShoppingListDao) {

    fun saveShoppingList(shoppingList: ShoppingList): Completable {
        return shoppingListDao.saveShoppingList(shoppingList)
    }
}
