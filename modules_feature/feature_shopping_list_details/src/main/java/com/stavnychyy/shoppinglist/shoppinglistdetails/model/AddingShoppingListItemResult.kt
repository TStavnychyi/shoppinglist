package com.stavnychyy.shoppinglist.shoppinglistdetails.model

import android.os.Parcelable
import com.stavnychyy.shoppinglist.parcelabledomain.PCLShoppingListItemId
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AddingShoppingListItemResult(
  private val pclShoppingListItemId: PCLShoppingListItemId?,
  val title: String,
  val subtitle: String
) : Parcelable {

  @IgnoredOnParcel
  val shoppingListItemId = pclShoppingListItemId?.toDomain()
}
