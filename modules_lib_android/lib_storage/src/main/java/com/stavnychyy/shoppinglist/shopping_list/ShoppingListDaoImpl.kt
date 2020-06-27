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
            .subscribeOn(Schedulers.io())
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

    override fun getAllShoppingListWithItems(shoppingListId: ShoppingListId): Single<ShoppingList> {
        return roomDao.getShoppingListWithItems(shoppingListId.id)
            .subscribeOn(Schedulers.io())
            .map { it.toDomain() }
    }

    override fun saveShoppingListItems(items: List<ShoppingListItem>): Completable {
        return roomDao.saveShoppingListItems(items.map { DBShoppingListItem(it) })
            .subscribeOn(Schedulers.io())
    }
}
