package com.stavnychyy.shoppinglist.addshoppinglist.viewmodel

import androidx.lifecycle.ViewModel
import com.stavnychyy.shoppinglist.addshoppinglist.model.*
import com.stavnychyy.shoppinglist.common.SHOPPING_LIST_DATE_FORMAT
import com.stavnychyy.shoppinglist.common.lifecycle.ClickEvent
import com.stavnychyy.shoppinglist.common.lifecycle.LiveEvent
import com.stavnychyy.shoppinglist.domain.ShoppingList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject

class AddShoppingListViewModel @Inject constructor(
  private val shoppingListRepository: AddShoppingListRepository
) : ViewModel() {

  private val disposables = CompositeDisposable()

  val onFormDataInvalidLiveEvent = LiveEvent<ClickEvent>()
  val onShoppingListAddedSuccessfully = LiveEvent<ClickEvent>()

  fun saveShoppingList(name: String, purchaseDate: LocalDate) {
    with(getFormValidationStatus(name, purchaseDate)) {
      if (this is FormValidStatus) {
        shoppingListRepository.saveShoppingList(
          ShoppingList(this.name, this.purchaseDate))
          .observeOn(AndroidSchedulers.mainThread())
          .subscribeBy(onComplete = { onShoppingListAddedSuccessfully.value = ClickEvent() })
          .addTo(disposables)
      } else {
        onFormDataInvalidLiveEvent.value = ClickEvent()
      }
    }
  }

  override fun onCleared() {
    disposables.dispose()
    super.onCleared()
  }

  fun formatSelectedDayOrMonth(number: Int): String {
    return if (number < 10) {
      String.format("%02d", number)
    } else number.toString()
  }

  fun formatSelectedDate(selectedDate: String): LocalDate {
    return LocalDate.parse(selectedDate, DateTimeFormatter.ofPattern(
      SHOPPING_LIST_DATE_FORMAT))
  }

  private fun getFormValidationStatus(name: String, purchaseDate: LocalDate): FormValidationStatus {
    val formattedName = name.trim()

    if (!isShoppingNameValid(formattedName) || !isShoppingPurchaseDateValid(purchaseDate)) {
      return FormInvalidStatus
    }

    return FormValidStatus(formattedName, purchaseDate)
  }
}
