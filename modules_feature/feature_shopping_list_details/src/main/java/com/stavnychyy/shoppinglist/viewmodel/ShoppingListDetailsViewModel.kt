package com.stavnychyy.shoppinglist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.toObservable
import com.stavnychyy.shoppinglist.ShoppingListId
import com.stavnychyy.shoppinglist.ShoppingListItem
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
  private lateinit var shoppingListId: ShoppingListId
  private val updatedItems: MutableSet<ShoppingListItem> = mutableSetOf()
  private val _shoppingListItemsLiveData = MutableLiveData<PagedList<ShoppingListItemViewEntity>>()
  val shoppingListItemsLiveData: LiveData<PagedList<ShoppingListItemViewEntity>> = _shoppingListItemsLiveData

  fun loadShoppingList(shoppingListId: ShoppingListId) {
    this.shoppingListId = shoppingListId
    shoppingListRepository.getShoppingListItems(shoppingListId)
      .map { ShoppingListItemViewEntity.create(it, ::deleteShoppingListItem, ::onShoppingListItemCheckedChanged) }
      .toObservable(pageSize = 10)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeBy(onNext = { _shoppingListItemsLiveData.value = it })
      .addTo(disposables)
  }

  fun addShoppingListItem(title: String, subtitle: String) {
    shoppingListRepository.saveShoppingListItem(ShoppingListItem(title, subtitle, false, shoppingListId))
      .subscribe()
      .addTo(disposables)
  }

  fun saveChanges() {
    if (updatedItems.isNotEmpty()) {
      shoppingListRepository.saveShoppingListItems(updatedItems.toList())
        .subscribeBy()
        .addTo(disposables)
    }
  }

  private fun deleteShoppingListItem(shoppingListItem: ShoppingListItem) {
    shoppingListRepository.deleteShoppingListItem(shoppingListItem)
      .subscribe()
      .addTo(disposables)
  }

  private fun onShoppingListItemCheckedChanged(shoppingListItem: ShoppingListItem) {
    updatedItems.add(shoppingListItem)
  }

  override fun onCleared() {
    disposables.dispose()
    super.onCleared()
  }
}
