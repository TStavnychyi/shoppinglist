package com.stavnychyy.shoppinglist.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stavnychyy.shoppinglist.fragment.BaseFragment


inline fun <reified T : ViewModel> BaseFragment.injectViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProvider(this, factory)[T::class.java]
}
