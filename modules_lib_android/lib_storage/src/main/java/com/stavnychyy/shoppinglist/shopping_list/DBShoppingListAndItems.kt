package com.stavnychyy.shoppinglist.shopping_list

import androidx.room.Embedded
import androidx.room.Relation
import com.stavnychyy.shoppinglist.ShoppingListItem


internal class DBShoppingListWithItems(
    @Embedded
    val shoppingList: DBShoppingList,
    @Relation(
        parentColumn = DBShoppingList.ID,
        entityColumn = DBShoppingListItem.SHOPPING_LIST_ID
    )
    val items: List<DBShoppingListItem>
)
