package com.stavnychyy.shoppinglist.common.extensions

import android.view.LayoutInflater
import android.view.View

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
