package com.stavnychyy.shoppinglist.shoppinglistdetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.toObservable
import com.stavnychyy.shoppinglist.domain.ShoppingListId
import com.stavnychyy.shoppinglist.domain.ShoppingListItem
import com.stavnychyy.shoppinglist.shoppinglistdetails.model.ShoppingListDetailsRepository
import com.stavnychyy.shoppinglist.shoppinglistdetails.view.adapter.ShoppingListItemViewEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class ShoppingListDetailsViewModel @Inject constructor(
  private val shoppingListDetailsRepository: ShoppingListDetailsRepository
) : ViewModel() {

  private val disposables = CompositeDisposable()
  private lateinit var shoppingListId: ShoppingListId
  private val updatedItems: MutableSet<ShoppingListItem> = mutableSetOf()
  private val _shoppingListItemsLiveData = MutableLiveData<PagedList<ShoppingListItemViewEntity>>()
  val shoppingListItemsLiveData: LiveData<PagedList<ShoppingListItemViewEntity>> = _shoppingListItemsLiveData

  fun loadShoppingList(shoppingListId: ShoppingListId) {
    this.shoppingListId = shoppingListId
    shoppingListDetailsRepository.getShoppingListItems(shoppingListId)
      .map { ShoppingListItemViewEntity.create(it, ::deleteShoppingListItem, ::onShoppingListItemCheckedChanged) }
      .toObservable(pageSize = 10)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeBy(onNext = { _shoppingListItemsLiveData.value = it })
      .addTo(disposables)
  }

  fun addShoppingListItem(title: String, subtitle: String) {
    shoppingListDetailsRepository.saveShoppingListItem(
      ShoppingListItem(title, subtitle, false, shoppingListId))
      .subscribe()
      .addTo(disposables)
  }

  fun saveChanges() {
    if (updatedItems.isNotEmpty()) {
      shoppingListDetailsRepository.saveShoppingListItems(updatedItems.toList())
        .subscribeBy()
        .addTo(disposables)
    }
  }

  private fun deleteShoppingListItem(shoppingListItem: ShoppingListItem) {
    shoppingListDetailsRepository.deleteShoppingListItem(shoppingListItem)
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
