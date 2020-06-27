package com.stavnychyy.shoppinglist.viewmodel

data class ShoppingListItemViewEntity(
    val title: String,
    val createdDate: String,
    val status: String,
    val onItemClickListener: () -> Unit
)
