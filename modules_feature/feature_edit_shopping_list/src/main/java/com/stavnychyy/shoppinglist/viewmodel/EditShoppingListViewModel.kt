package com.stavnychyy.shoppinglist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.stavnychyy.shoppinglist.ShoppingList
import com.stavnychyy.shoppinglist.ShoppingListId
import com.stavnychyy.shoppinglist.ShoppingListItem
import com.stavnychyy.shoppinglist.di.EditShoppingListScope
import com.stavnychyy.shoppinglist.extensions.plusAssign
import com.stavnychyy.shoppinglist.model.EditShoppingListRepository
import com.stavnychyy.shoppinglist.view.adapter.ShoppingListItemViewEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

@EditShoppingListScope
class EditShoppingListViewModel @Inject constructor(
    private val shoppingListRepository: EditShoppingListRepository
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _shoppingListViewEntity = MutableLiveData<EditShoppingListViewEntity>()
    val shoppingListViewEntity: LiveData<EditShoppingListViewEntity> = _shoppingListViewEntity

    private val _shoppingListItemsLiveData = MutableLiveData<MutableList<ShoppingListItem>>(mutableListOf())
    val shoppingListItemsLiveData: LiveData<List<ShoppingListItemViewEntity>> =
        Transformations.map(_shoppingListItemsLiveData) {
            it.map { ShoppingListItemViewEntity.create(it) }
        }

    private lateinit var shoppingList: ShoppingList

    fun loadShoppingList(shoppingListId: ShoppingListId) {
        shoppingListRepository.getShoppingListById(shoppingListId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    _shoppingListViewEntity.value = createEditShoppingListViewEntity(it)
                    shoppingList = it
                },
                onError = {
                    // TODO: 27/06/2020  open error screen
                }
            ).addTo(disposables)
    }

    fun saveShoppingList() {
//        shoppingList.copy(items = shoppingListItemsLiveData.value.map {  })
        shoppingListRepository.saveShoppingListWithItems(shoppingList)
    }

    fun addShoppingListItem(title: String, subtitle: String) {
        _shoppingListItemsLiveData.plusAssign(ShoppingListItem(title, subtitle, false, shoppingListId))
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }


    private fun createEditShoppingListViewEntity(shoppingList: ShoppingList): EditShoppingListViewEntity {
        return with(shoppingList) {
            EditShoppingListViewEntity(name, items.map { ShoppingListItemViewEntity.create(it) })
        }
    }
}
