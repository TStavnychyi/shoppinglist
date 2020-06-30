package com.stavnychyy.shoppinglist.common.lifecycle

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class LiveEvent<T> {
  private val liveData = MutableLiveData<Event<T>>()

  var value: T?
    set(value) {
      value?.let { liveData.value = Event(it) }
    }
    get() = liveData.value?.peekContent()

  fun observeForever(observer: Observer<Event<T>>) {
    liveData.observeForever(observer)
  }

  fun removeObserver(observer: Observer<Event<T>>) {
    liveData.removeObserver(observer)
  }

  fun observe(viewLifecycleOwner: LifecycleOwner, eventObserver: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner, Observer { event ->
      event.getContentIfNotHandled()?.let { eventObserver(it) }
    })
  }
}

class Event<out T>(private val content: T) {

  private var hasBeenHandled = false

  fun getContentIfNotHandled(): T? {
    return if (!hasBeenHandled) content.also { hasBeenHandled = true } else null
  }

  fun peekContent(): T = content
}
