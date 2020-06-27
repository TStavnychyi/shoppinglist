package com.stavnychyy.shoppinglist.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes


fun LayoutInflater.inflateNoAttach(@LayoutRes layoutId: Int, parent: ViewGroup?): View {
    return inflate(layoutId, parent, false)
}
