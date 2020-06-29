package com.stavnychyy.shoppinglist.shopping_list

import androidx.paging.DataSource
import com.stavnychyy.shoppinglist.ShoppingList
import com.stavnychyy.shoppinglist.ShoppingListId
import com.stavnychyy.shoppinglist.ShoppingListItem
import com.stavnychyy.shoppinglist.toDomain
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

internal class ShoppingListDaoImpl(private val roomDao: RoomShoppingListDao) : ShoppingListDao {

    override fun saveShoppingList(shoppingList: ShoppingList): Completable {
        return roomDao.saveShoppingList(DBShoppingList(shoppingList))
    }

    override fun updateShoppingList(shoppingList: ShoppingList): Completable {
        return roomDao.updateShoppingList(DBShoppingList(shoppingList))
    }

    override fun getShoppingListById(shoppingListId: ShoppingListId): Maybe<ShoppingList> {
        return roomDao.getShoppingListById(shoppingListId.id)
            .subscribeOn(Schedulers.io())
            .map { it.toDomain() }
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
