package com.stavnychyy.shoppinglist.view

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ShoppingListItemResult(val title: String, val subtitle: String) : Parcelable
