package com.stavnychyy.shoppinglist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.toObservable
import com.stavnychyy.shoppinglist.SHOPPING_LIST_DATE_FORMAT
import com.stavnychyy.shoppinglist.ShoppingList
import com.stavnychyy.shoppinglist.ShoppingListId
import com.stavnychyy.shoppinglist.model.ShoppingListRepository
import com.stavnychyy.shoppinglist.navigation.AppNavigator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject

class ShoppingListViewModel @Inject constructor(
  private val shoppingListRepository: ShoppingListRepository
) : ViewModel() {

  private val disposables = CompositeDisposable()
  private lateinit var appNavigator: AppNavigator

  private val _shoppingListsViewEntity = MutableLiveData<PagedList<ShoppingListItemViewEntity>>()
  val shoppingListViewEntity: LiveData<PagedList<ShoppingListItemViewEntity>> =
    _shoppingListsViewEntity

  fun setAppNavigator(appNavigator: AppNavigator) {
    this.appNavigator = appNavigator
  }

  fun getShoppingLists() {
    shoppingListRepository.getAllShoppingLists()
      .map { createShoppingListItemViewEntity(it) }
      .toObservable(pageSize = 10)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeBy(onNext = { _shoppingListsViewEntity.value = it })
      .addTo(disposables)
  }

  override fun onCleared() {
    disposables.dispose()
    super.onCleared()
  }

  private fun openShoppingListScreen(shoppingListId: ShoppingListId, shoppingListTitle: String) {
    appNavigator.opeShoppingListDetailsScreen(shoppingListId, shoppingListTitle, false)
  }

  private fun archiveShoppingList(shoppingList: ShoppingList) {
    shoppingListRepository.updateShoppingList(shoppingList.copy(isArchived = true))
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe()
      .addTo(disposables)
  }

  private fun deleteShoppingList(shoppingList: ShoppingList) {
    shoppingListRepository.deleteShoppingListWithItems(shoppingList)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeBy()
      .addTo(disposables)
  }

  private fun createShoppingListItemViewEntity(shoppingList: ShoppingList): ShoppingListItemViewEntity {
    return with(shoppingList) {
      ShoppingListItemViewEntity(
        name,
        purchaseDate.format(DateTimeFormatter.ofPattern(SHOPPING_LIST_DATE_FORMAT)),
        shoppingList,
        onArchiveItemClickListener = { archiveShoppingList(it) },
        onDeleteItemClickListener = { deleteShoppingList(it) },
        onItemClickListener = { id?.let { openShoppingListScreen(it, shoppingList.name) } }
      )
    }
  }
}
