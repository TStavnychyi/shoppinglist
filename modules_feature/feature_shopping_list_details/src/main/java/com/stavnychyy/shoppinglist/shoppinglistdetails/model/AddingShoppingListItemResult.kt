package com.stavnychyy.shoppinglist.shoppinglistdetails.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class AddingShoppingListItemResult(val title: String, val subtitle: String) : Parcelable
