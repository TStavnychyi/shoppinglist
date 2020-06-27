package com.stavnychyy.shoppinglist.shopping_list

import androidx.paging.DataSource
import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import com.stavnychyy.shoppinglist.shopping_list.DBShoppingList.Companion.ID
import com.stavnychyy.shoppinglist.shopping_list.DBShoppingList.Companion.SHOPPING_LIST_TABLE_NAME
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
internal interface RoomShoppingListDao {

    @Insert(onConflict = REPLACE)
    fun saveShoppingList(shoppingList: DBShoppingList): Completable

    @Query("SELECT * FROM $SHOPPING_LIST_TABLE_NAME WHERE $ID = :id")
    fun getShoppingListById(id: Int): Maybe<DBShoppingList>

    @Query("SELECT * FROM $SHOPPING_LIST_TABLE_NAME")
    fun getAllShoppingLists(): DataSource.Factory<Int, DBShoppingList>

    @Transaction
    @Query("SELECT * FROM $SHOPPING_LIST_TABLE_NAME WHERE $ID = :shoppingListId")
    fun getShoppingListWithItems(shoppingListId: Int): Single<DBShoppingListWithItems>

    @Insert(onConflict = IGNORE)
    fun saveShoppingListItems(items: List<DBShoppingListItem>): Completable
}
