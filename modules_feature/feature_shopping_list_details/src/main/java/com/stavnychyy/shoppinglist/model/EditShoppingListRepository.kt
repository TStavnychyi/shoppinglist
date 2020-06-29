package com.stavnychyy.shoppinglist.model

import androidx.paging.DataSource
import com.stavnychyy.shoppinglist.ShoppingListId
import com.stavnychyy.shoppinglist.ShoppingListItem
import com.stavnychyy.shoppinglist.shopping_list.ShoppingListDao
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class EditShoppingListRepository @Inject constructor(private val shoppingListDao: ShoppingListDao) {

  fun getShoppingListItems(shoppingListId: ShoppingListId): DataSource.Factory<Int, ShoppingListItem> {
    return shoppingListDao.getShoppingListWithItems(shoppingListId)
  }

  fun saveShoppingListItem(item: ShoppingListItem): Completable {
    return shoppingListDao.saveShoppingListItem(item)
  }

  fun saveShoppingListItems(items: List<ShoppingListItem>): Completable {
    return shoppingListDao.saveShoppingListItems(items)
  }

  fun deleteShoppingListItem(item: ShoppingListItem): Completable {
    return shoppingListDao.deleteShoppingListItem(item)
  }
}
