package com.stavnychyy.shoppinglist

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class PCLShoppingListId(private val id: Int) : Parcelable {

    constructor(shoppingListId: ShoppingListId) : this(shoppingListId.id)

    fun toDomain() = ShoppingListId(id)
}
