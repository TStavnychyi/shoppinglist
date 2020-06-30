package com.stavnychyy.shoppinglist.tests.util

import androidx.lifecycle.Observer
import com.stavnychyy.shoppinglist.common.lifecycle.Event
import com.stavnychyy.shoppinglist.common.lifecycle.LiveEvent

class TestObserver<T> : Observer<Event<T>> {

  val observedValues = mutableListOf<Event<T>?>()

  override fun onChanged(value: Event<T>?) {
    observedValues.add(value)
  }
}

fun <T> LiveEvent<T>.testObserver() = TestObserver<T>().also {
  observeForever(it)
}
