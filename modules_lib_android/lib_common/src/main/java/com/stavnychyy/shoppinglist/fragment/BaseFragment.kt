package com.stavnychyy.shoppinglist.fragment

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes private val layoutId: Int) : Fragment(layoutId) {

    fun changeToolbarTitle(title: String) {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = title
    }
}
