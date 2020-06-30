package com.stavnychyy.shoppinglist.common.extensions

import androidx.lifecycle.MutableLiveData


fun <T> MutableLiveData<MutableList<T>>.plusAssign(item: T) {
    val value = this.value ?: mutableListOf()
    value.add(item)
    this.value = value
}
