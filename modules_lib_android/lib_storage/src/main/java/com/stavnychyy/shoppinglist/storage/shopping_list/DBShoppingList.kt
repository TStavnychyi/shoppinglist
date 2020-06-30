package com.stavnychyy.shoppinglist.storage.shopping_list

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.stavnychyy.shoppinglist.domain.ShoppingList
import com.stavnychyy.shoppinglist.storage.shopping_list.DBShoppingList.Companion.SHOPPING_LIST_TABLE_NAME
import org.threeten.bp.LocalDate

@Entity(tableName = SHOPPING_LIST_TABLE_NAME)
internal class DBShoppingList(
  @ColumnInfo(name = ID) @PrimaryKey(autoGenerate = true) var id: Int?,
  @ColumnInfo(name = NAME) var name: String,
  @ColumnInfo(name = PURCHASE_DATE) var purchaseDate: LocalDate,
  @ColumnInfo(name = IS_ARCHIVED) var isArchived: Boolean
) {

  constructor(shoppingList: ShoppingList) : this(
    shoppingList.id?.id,
    shoppingList.name,
    shoppingList.purchaseDate,
    shoppingList.isArchived
  )

  companion object {
    const val SHOPPING_LIST_TABLE_NAME = "SHOPPING_LIST"
    const val ID = "ID"
    const val NAME = "NAME"
    const val PURCHASE_DATE = "PURCHASE_DATE"
    const val IS_ARCHIVED = "IS_ARCHIVED"
  }
}
