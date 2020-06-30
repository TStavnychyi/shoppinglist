package com.stavnychyy.shoppinglist.tests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.stavnychyy.shoppinglist.addshoppinglist.model.AddShoppingListRepository
import com.stavnychyy.shoppinglist.addshoppinglist.viewmodel.AddShoppingListViewModel
import com.stavnychyy.shoppinglist.common.lifecycle.ClickEvent
import com.stavnychyy.shoppinglist.common.lifecycle.Event
import com.stavnychyy.shoppinglist.domain.ShoppingList
import com.stavnychyy.shoppinglist.tests.util.RxSchedulerRule
import com.stavnychyy.shoppinglist.tests.util.testObserver
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Completable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.threeten.bp.LocalDate

@RunWith(JUnit4::class)
class AddShoppingListViewModelTest {

  @get:Rule
  val rxSchedulerRule = RxSchedulerRule()

  @get:Rule
  var instantTaskExecutorRule = InstantTaskExecutorRule()

  private lateinit var viewModel: AddShoppingListViewModel

  private lateinit var repository: AddShoppingListRepository

  private lateinit var observer: Observer<Event<ClickEvent>>

  @Before
  fun setUp() {
    RxAndroidPlugins.setInitMainThreadSchedulerHandler { handler ->
      Schedulers.trampoline()
    }

    repository = mockk(relaxed = true)
    observer = mockk(relaxed = true)
    viewModel = AddShoppingListViewModel(repository)
  }

  @Test
  fun `call formatSelectedDayOrMonth to get given day in two-digit format`() {
    // given
    val givenDay = 8
    val expectedDay = "08"

    // when
    val actualDay = viewModel.formatSelectedDayOrMonth(givenDay)

    // then
    assertEquals(expectedDay, actualDay)
  }

  @Test
  fun `should save shopping list notify view about invalid data`() {
    // given
    val givenName = "Random list name"
    val givenDate = LocalDate.now().minusDays(1)
    val givenShoppingList = ShoppingList(givenName, givenDate)

    // when
    val liveEventUnderTest = viewModel.onFormDataInvalidLiveEvent.testObserver()

    viewModel.saveShoppingList(givenName, givenDate)

    // then
    assertTrue(liveEventUnderTest.observedValues.isNotEmpty() && liveEventUnderTest.observedValues.first() is Event)
  }

  @Test
  fun `save shopping list and get notified about successful operation`() {
    // given
    val givenName = "Random list name"
    val givenDate = LocalDate.now()
    val givenShoppingList = ShoppingList(givenName, givenDate)

    // when
    val liveEventUnderTest = viewModel.onShoppingListAddedSuccessfully.testObserver()

    every { repository.saveShoppingList(givenShoppingList) } returns Completable.complete()
    viewModel.saveShoppingList(givenName, givenDate)

    // then
    assertTrue(liveEventUnderTest.observedValues.isNotEmpty() && liveEventUnderTest.observedValues.first() is Event)
  }
}
