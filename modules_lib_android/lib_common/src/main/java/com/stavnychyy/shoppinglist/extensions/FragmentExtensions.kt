package com.stavnychyy.shoppinglist.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Fragment.changeToolbarTitle(title: String) {
  (requireActivity() as AppCompatActivity).supportActionBar?.title = title
}
