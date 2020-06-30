package com.stavnychyy.shoppinglist.common.extensions

import android.widget.EditText
import android.widget.TextView


fun TextView.getString(): String {
    return text.toString()
}

fun EditText.placeCursorToEnd() {
    this.setSelection(this.text.length)
}
