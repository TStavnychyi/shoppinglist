package com.stavnychyy.shoppinglist.shopping_list

import androidx.paging.DataSource
import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import com.stavnychyy.shoppinglist.shopping_list.DBShoppingList.Companion.ID
import com.stavnychyy.shoppinglist.shopping_list.DBShoppingList.Companion.IS_ARCHIVED
import com.stavnychyy.shoppinglist.shopping_list.DBShoppingList.Companion.PURCHASE_DATE
import com.stavnychyy.shoppinglist.shopping_list.DBShoppingList.Companion.SHOPPING_LIST_TABLE_NAME
import com.stavnychyy.shoppinglist.shopping_list.DBShoppingListItem.Companion.SHOPPING_LIST_ID
import com.stavnychyy.shoppinglist.shopping_list.DBShoppingListItem.Companion.SHOPPING_LIST_ITEM_TABLE_NAME
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
internal interface RoomShoppingListDao {

    // SHOPPING LIST

    @Insert(onConflict = IGNORE)
    fun saveShoppingList(shoppingList: DBShoppingList): Completable

    @Update(onConflict = REPLACE)
    fun updateShoppingList(shoppingList: DBShoppingList): Completable

    @Query("SELECT * FROM $SHOPPING_LIST_TABLE_NAME WHERE $ID = :id")
    fun getShoppingListById(id: Int): Maybe<DBShoppingList>

    @Query("SELECT * FROM $SHOPPING_LIST_TABLE_NAME WHERE $IS_ARCHIVED = 0 ORDER BY $PURCHASE_DATE ASC")
    fun getAllShoppingLists(): DataSource.Factory<Int, DBShoppingListWithItems>

    @Query("SELECT * FROM $SHOPPING_LIST_TABLE_NAME WHERE $IS_ARCHIVED = 1 ORDER BY $PURCHASE_DATE ASC")
    fun getArchivedShoppingLists(): DataSource.Factory<Int, DBShoppingListWithItems>

    @Delete
    fun deleteShoppingList(shoppingListWithItems: DBShoppingList): Completable


    // SHOPPING LIST ITEMS

    @Query("SELECT * FROM $SHOPPING_LIST_ITEM_TABLE_NAME WHERE $SHOPPING_LIST_ID = :shoppingListId")
    fun getShoppingListWithItems(shoppingListId: Int): DataSource.Factory<Int, DBShoppingListItem>

    @Insert(onConflict = REPLACE)
    fun saveShoppingListItems(items: List<DBShoppingListItem>): Completable

    @Insert(onConflict = REPLACE)
    fun saveShoppingListItem(items: DBShoppingListItem): Completable

    @Delete
    fun deleteShoppingListItem(shoppingListItem: DBShoppingListItem): Completable
}
