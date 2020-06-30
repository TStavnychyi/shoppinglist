package com.stavnychyy.shoppinglist.storage.shopping_list

import androidx.paging.DataSource
import com.stavnychyy.shoppinglist.domain.ShoppingList
import com.stavnychyy.shoppinglist.domain.ShoppingListId
import com.stavnychyy.shoppinglist.domain.ShoppingListItem
import io.reactivex.Completable
import io.reactivex.Maybe

interface ShoppingListDao {
    fun saveShoppingList(shoppingList: ShoppingList): Completable
    fun updateShoppingList(shoppingList: ShoppingList): Completable
    fun getAllShoppingLists(): DataSource.Factory<Int, ShoppingList>
    fun getShoppingListWithItems(shoppingListId: ShoppingListId): DataSource.Factory<Int, ShoppingListItem>
    fun saveShoppingListItem(item: ShoppingListItem): Completable
    fun saveShoppingListItems(items: List<ShoppingListItem>): Completable
    fun getArchivedShoppingLists(): DataSource.Factory<Int, ShoppingList>
    fun deleteShoppingListWithItems(shoppingList: ShoppingList): Completable
    fun deleteShoppingListItem(shoppingListItem: ShoppingListItem): Completable
}
