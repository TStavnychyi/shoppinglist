package com.stavnychyy.shoppinglist.model

import com.stavnychyy.shoppinglist.ShoppingList
import com.stavnychyy.shoppinglist.ShoppingListId
import com.stavnychyy.shoppinglist.shopping_list.ShoppingListDao
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject


class EditShoppingListRepository @Inject constructor(private val shoppingListDao: ShoppingListDao) {

    fun getShoppingListById(shoppingListId: ShoppingListId): Single<ShoppingList> {
        return shoppingListDao.getShoppingListById(shoppingListId).toSingle()
    }

    fun saveShoppingListWithItems(shoppingList: ShoppingList): Completable {
        return shoppingListDao.saveShoppingList(shoppingList)
    }
}
