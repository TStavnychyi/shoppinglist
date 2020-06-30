package com.stavnychyy.shoppinglist.storage.shopping_list

import androidx.paging.DataSource
import com.stavnychyy.shoppinglist.domain.ShoppingList
import com.stavnychyy.shoppinglist.domain.ShoppingListId
import com.stavnychyy.shoppinglist.domain.ShoppingListItem
import com.stavnychyy.shoppinglist.storage.toDomain
import io.reactivex.Completable

internal class ShoppingListDaoImpl(private val roomDao: RoomShoppingListDao) : ShoppingListDao {

  override fun saveShoppingList(shoppingList: ShoppingList): Completable {
    return roomDao.saveShoppingList(DBShoppingList(shoppingList))
  }

  override fun updateShoppingList(shoppingList: ShoppingList): Completable {
    return roomDao.updateShoppingList(DBShoppingList(shoppingList))
  }

  override fun getAllShoppingLists(): DataSource.Factory<Int, ShoppingList> {
    return roomDao.getAllShoppingLists()
      .map { it.toDomain() }
  }

  override fun getShoppingListWithItems(shoppingListId: ShoppingListId): DataSource.Factory<Int, ShoppingListItem> {
    return roomDao.getShoppingListWithItems(shoppingListId.id)
      .map { it.toDomain() }
  }

  override fun saveShoppingListItem(item: ShoppingListItem): Completable {
    return roomDao.saveShoppingListItem(DBShoppingListItem(item))
  }

  override fun saveShoppingListItems(items: List<ShoppingListItem>): Completable {
    return roomDao.saveShoppingListItems(items.map { DBShoppingListItem(it) })
  }

  override fun getArchivedShoppingLists(): DataSource.Factory<Int, ShoppingList> {
    return roomDao.getArchivedShoppingLists()
      .map { it.toDomain() }
  }

  override fun deleteShoppingListWithItems(shoppingList: ShoppingList): Completable {
    return roomDao.deleteShoppingList(DBShoppingList(shoppingList))
  }

  override fun deleteShoppingListItem(shoppingListItem: ShoppingListItem): Completable {
    return roomDao.deleteShoppingListItem(DBShoppingListItem(shoppingListItem))
  }
}
