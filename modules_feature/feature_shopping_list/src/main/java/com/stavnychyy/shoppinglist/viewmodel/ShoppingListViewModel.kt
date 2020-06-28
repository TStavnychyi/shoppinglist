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
  private val shoppingListRepository: ShoppingListRepository,
  private val appNavigator: AppNavigator
) : ViewModel() {

  private val disposables = CompositeDisposable()

  private val _shoppingListsViewEntity = MutableLiveData<PagedList<ShoppingListItemViewEntity>>()
  val shoppingListViewEntity: LiveData<PagedList<ShoppingListItemViewEntity>> = _shoppingListsViewEntity

  fun getShoppingLists() {
    shoppingListRepository.getAllShoppingLists()
      .map { createShoppingListItemViewEntity(it) }
      .toObservable(pageSize = 10)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeBy(
        onNext = { _shoppingListsViewEntity.value = it },
        onError = { // TODO: 27/06/2020  show error screen
        }
      ).addTo(disposables)
  }

  override fun onCleared() {
    disposables.dispose()
    super.onCleared()
  }

  private fun openShoppingListScreen(shoppingListId: ShoppingListId) {
    appNavigator.opeShoppingListDetailsScreen(shoppingListId, false)
  }

  private fun archiveShoppingList(shoppingList: ShoppingList) {
    shoppingListRepository.updateShoppingList(shoppingList.copy(isArchived = true))
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeBy(
        onError = {
          // TODO: 28/06/2020  open error screen
        }).addTo(disposables)
  }

  private fun deleteShoppingList(shoppingList: ShoppingList) {
    shoppingListRepository.deleteShoppingListWithItems(shoppingList)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeBy(
        onError = {
          // TODO: 28/06/2020  open error screen
        }
      )
      .addTo(disposables)
  }

  private fun createShoppingListItemViewEntity(shoppingList: ShoppingList): ShoppingListItemViewEntity {
    return with(shoppingList) {
      ShoppingListItemViewEntity(
        name,
        purchaseDate.format(DateTimeFormatter.ofPattern(SHOPPING_LIST_DATE_FORMAT)),
        "0/0",
        shoppingList,
        onArchiveItemClickListener = { archiveShoppingList(it) },
        onDeleteItemClickListener = { deleteShoppingList(it) },
        onItemClickListener = { id?.let { openShoppingListScreen(it) } }
      )
    }
  }
}
