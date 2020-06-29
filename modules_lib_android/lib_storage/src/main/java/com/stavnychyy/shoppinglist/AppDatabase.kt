package com.stavnychyy.shoppinglist

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.stavnychyy.shoppinglist.converter.DateConverter
import com.stavnychyy.shoppinglist.shopping_list.DBShoppingList
import com.stavnychyy.shoppinglist.shopping_list.DBShoppingListItem
import com.stavnychyy.shoppinglist.shopping_list.RoomShoppingListDao
import com.stavnychyy.shoppinglist.shopping_list.ShoppingListDao
import com.stavnychyy.shoppinglist.shopping_list.ShoppingListDaoImpl

@Database(entities = [DBShoppingList::class, DBShoppingListItem::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase(), DatabaseProvider {

    internal abstract val shoppingListDao: RoomShoppingListDao

    override fun createShoppingListDao(): ShoppingListDao {
        return ShoppingListDaoImpl(shoppingListDao)
    }
}
