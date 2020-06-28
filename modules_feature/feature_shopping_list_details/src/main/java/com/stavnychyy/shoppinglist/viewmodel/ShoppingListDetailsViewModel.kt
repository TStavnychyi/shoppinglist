package com.stavnychyy.shoppinglist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.stavnychyy.shoppinglist.ShoppingList
import com.stavnychyy.shoppinglist.ShoppingListId
import com.stavnychyy.shoppinglist.ShoppingListItem
import com.stavnychyy.shoppinglist.extensions.plusAssign
import com.stavnychyy.shoppinglist.lifecycle.LiveEvent
import com.stavnychyy.shoppinglist.model.EditShoppingListRepository
import com.stavnychyy.shoppinglist.view.adapter.ShoppingListItemViewEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class ShoppingListDetailsViewModel @Inject constructor(
  private val shoppingListRepository: EditShoppingListRepository
) : ViewModel() {

  private val disposables = CompositeDisposable()
  private val _shoppingListItemsLiveData = MutableLiveData<MutableList<ShoppingListItem>>()
  private lateinit var shoppingList: ShoppingList
  private lateinit var shoppingListId: ShoppingListId
  private val newShoppingListItems = mutableListOf<ShoppingListItem>()

  val shoppingListItemsLiveData: LiveData<List<ShoppingListItemViewEntity>> =
    Transformations.map(_shoppingListItemsLiveData) {
      it.map { ShoppingListItemViewEntity.create(it) }
    }

  val changeFragmentTitleLiveEvent = LiveEvent<String>()

  fun loadShoppingList(shoppingListId: ShoppingListId) {
    this.shoppingListId = shoppingListId
    shoppingListRepository.getShoppingListWithItems(shoppingListId)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeBy(
        onSuccess = {
          changeFragmentTitleLiveEvent.value = it.name
          _shoppingListItemsLiveData.value = it.items.toMutableList()
          shoppingList = it
        },
        onError = {
          // TODO: 27/06/2020  open error screen
        }
      ).addTo(disposables)
  }

  fun saveShoppingList() {
    if (newShoppingListItems.isNotEmpty()) {
      shoppingListRepository.saveShoppingListItems(newShoppingListItems)
        .subscribeBy(
          onError = {
            // TODO: 27/06/2020  open error screen
          }).addTo(disposables)
    }
  }

  fun addShoppingListItem(title: String, subtitle: String) {
    with(ShoppingListItem(title, subtitle, false, shoppingListId)) {
      newShoppingListItems.add(this)
      _shoppingListItemsLiveData.plusAssign(this)
    }
  }

  override fun onCleared() {
    disposables.dispose()
    super.onCleared()
  }
}
