package com.stavnychyy.shoppinglist.parcelabledomain

import android.os.Parcelable
import com.stavnychyy.shoppinglist.domain.ShoppingListId
import kotlinx.android.parcel.Parcelize

@Parcelize
class PCLShoppingListId(private val id: Int) : Parcelable {

  constructor(shoppingListId: ShoppingListId) : this(shoppingListId.id)

  fun toDomain() = ShoppingListId(id)
}
