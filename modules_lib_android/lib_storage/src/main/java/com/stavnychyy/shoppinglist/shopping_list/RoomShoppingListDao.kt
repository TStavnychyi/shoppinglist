package com.stavnychyy.shoppinglist.shopping_list

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.stavnychyy.shoppinglist.shopping_list.DBShoppingList.Companion.ID
import com.stavnychyy.shoppinglist.shopping_list.DBShoppingList.Companion.IS_ARCHIVED
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

  @Query("SELECT * FROM $SHOPPING_LIST_TABLE_NAME WHERE $IS_ARCHIVED = 0")
  fun getAllShoppingLists(): DataSource.Factory<Int, DBShoppingList>

  @Transaction
  @Query("SELECT * FROM $SHOPPING_LIST_TABLE_NAME WHERE $ID = :shoppingListId")
  fun getShoppingListWithItems(shoppingListId: Int): Single<DBShoppingListWithItems>

  @Insert(onConflict = ABORT)
  fun saveShoppingListItems(items: List<DBShoppingListItem>): Completable

  @Query("SELECT * FROM $SHOPPING_LIST_TABLE_NAME WHERE $IS_ARCHIVED = 1")
  fun getArchivedShoppingLists(): DataSource.Factory<Int, DBShoppingList>

  @Delete
  fun deleteShoppingList(shoppingListWithItems: DBShoppingList): Completable
}
