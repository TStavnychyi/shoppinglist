package com.stavnychyy.shoppinglist.lifecycle

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

    fun postValue(value: T) {
        liveData.postValue(Event(value))
    }

    fun observe(viewLifecycleOwner: LifecycleOwner, eventObserver: (T) -> Unit) {
        liveData.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let { eventObserver(it) }
        })
    }
}

private class Event<out T>(private val content: T) {

    private var hasBeenHandled = false

    fun getContentIfNotHandled(): T? {
        return if (!hasBeenHandled) content.also { hasBeenHandled = true } else null
    }

    fun peekContent(): T = content
}
