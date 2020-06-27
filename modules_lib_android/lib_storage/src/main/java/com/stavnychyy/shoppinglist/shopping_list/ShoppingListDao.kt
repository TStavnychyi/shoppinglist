package com.stavnychyy.shoppinglist.shopping_list

import androidx.paging.DataSource
import com.stavnychyy.shoppinglist.ShoppingList
import com.stavnychyy.shoppinglist.ShoppingListId
import com.stavnychyy.shoppinglist.ShoppingListItem
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single


interface ShoppingListDao {
    fun saveShoppingList(shoppingList: ShoppingList): Completable
    fun getShoppingListById(shoppingListId: ShoppingListId): Maybe<ShoppingList>
    fun getAllShoppingLists(): DataSource.Factory<Int, ShoppingList>
    fun getAllShoppingListWithItems(shoppingListId: ShoppingListId): Single<ShoppingList>
    fun saveShoppingListItems(items: List<ShoppingListItem>): Completable
}
