package com.stavnychyy.shoppinglist.viewmodel

import com.stavnychyy.shoppinglist.view.adapter.ShoppingListItemViewEntity


data class EditShoppingListViewEntity(val title: String, val items: List<ShoppingListItemViewEntity>)
