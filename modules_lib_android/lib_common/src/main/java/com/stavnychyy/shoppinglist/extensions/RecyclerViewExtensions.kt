package com.stavnychyy.shoppinglist.extensions

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


fun RecyclerView.linearVertical() {
    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
}
