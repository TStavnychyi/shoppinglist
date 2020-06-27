package com.stavnychyy.shoppinglist.shopping_list

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.stavnychyy.shoppinglist.ShoppingListItem
import com.stavnychyy.shoppinglist.shopping_list.DBShoppingListItem.Companion.SHOPPING_LIST_ITEM_TABLE_NAME


@Entity(tableName = SHOPPING_LIST_ITEM_TABLE_NAME)
internal class DBShoppingListItem(
    @ColumnInfo(name = ID) @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = TITLE) val title: String,
    @ColumnInfo(name = NOTES) val notes: String,
    @ColumnInfo(name = IS_CHECKED) val isChecked: Boolean,
    @ColumnInfo(name = SHOPPING_LIST_ID) val shoppingListId: Int
) {

    constructor(shoppingListItem: ShoppingListItem) : this(
        null,
        shoppingListItem.title,
        shoppingListItem.notes,
        shoppingListItem.isChecked,
        shoppingListItem.shoppingListId.id
    )

    companion object {
        const val SHOPPING_LIST_ITEM_TABLE_NAME = "SHOPPING_LIST_ITEM"
        const val ID = "ID"
        const val TITLE = "TITLE"
        const val NOTES = "NOTES"
        const val IS_CHECKED = "IS_CHECKED"
        const val SHOPPING_LIST_ID = "SHOPPING_LIST_ID"
    }
}
