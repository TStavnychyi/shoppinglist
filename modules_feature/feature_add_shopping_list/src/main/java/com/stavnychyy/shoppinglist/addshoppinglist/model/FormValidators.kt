package com.stavnychyy.shoppinglist.addshoppinglist.model

import org.threeten.bp.LocalDate


fun isShoppingNameValid(name: String): Boolean {
    return name.trim().isNotEmpty()
}

fun isShoppingPurchaseDateValid(purchaseDate: LocalDate): Boolean {
    return !purchaseDate.isBefore(LocalDate.now())
}
