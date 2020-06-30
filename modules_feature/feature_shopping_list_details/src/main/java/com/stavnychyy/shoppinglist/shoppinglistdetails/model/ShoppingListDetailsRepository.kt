package com.stavnychyy.shoppinglist.shoppinglistdetails.model

import androidx.paging.DataSource
import com.stavnychyy.shoppinglist.domain.ShoppingListId
import com.stavnychyy.shoppinglist.domain.ShoppingListItem
import com.stavnychyy.shoppinglist.storage.shopping_list.ShoppingListDao
import io.reactivex.Completable
import javax.inject.Inject

class ShoppingListDetailsRepository @Inject constructor(private val shoppingListDao: ShoppingListDao) {

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
