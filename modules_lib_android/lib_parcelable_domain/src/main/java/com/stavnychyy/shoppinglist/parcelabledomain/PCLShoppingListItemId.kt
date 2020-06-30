package com.stavnychyy.shoppinglist.parcelabledomain

import android.os.Parcelable
import com.stavnychyy.shoppinglist.domain.ShoppingListItemId
import kotlinx.android.parcel.Parcelize

@Parcelize
class PCLShoppingListItemId(private val id: Int) : Parcelable {

  constructor(shoppingListItemId: ShoppingListItemId) : this(shoppingListItemId.id)

  fun toDomain() = ShoppingListItemId(id)
}
