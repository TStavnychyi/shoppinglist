package com.stavnychyy.shoppinglist.model

import com.stavnychyy.shoppinglist.ShoppingList
import com.stavnychyy.shoppinglist.ShoppingListId
import com.stavnychyy.shoppinglist.ShoppingListItem
import com.stavnychyy.shoppinglist.shopping_list.ShoppingListDao
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject


class EditShoppingListRepository @Inject constructor(private val shoppingListDao: ShoppingListDao) {

    fun getShoppingListWithItems(shoppingListId: ShoppingListId): Single<ShoppingList> {
        return shoppingListDao.getShoppingListWithItems(shoppingListId)
    }

    fun saveShoppingListItems(items: List<ShoppingListItem>): Completable {
        return shoppingListDao.saveShoppingListItems(items)
    }
}
