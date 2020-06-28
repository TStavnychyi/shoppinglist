package com.stavnychyy.shoppinglist.extensions

import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView

fun View.layoutInflater(): LayoutInflater {
    return LayoutInflater.from(context)
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.visibleOrGone(visible: Boolean) {
    if (visible) {
        visible()
    } else {
        gone()
    }
}
