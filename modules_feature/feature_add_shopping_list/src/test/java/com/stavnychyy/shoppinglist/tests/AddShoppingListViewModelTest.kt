package com.stavnychyy.shoppinglist.tests

import com.stavnychyy.shoppinglist.model.AddShoppingListRepository
import com.stavnychyy.shoppinglist.viewmodel.AddShoppingListViewModel
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
internal class AddShoppingListViewModelTest {

  private lateinit var viewModel: AddShoppingListViewModel
  private lateinit var repository: AddShoppingListRepository

  @Before
  fun setUp() {
    repository = mockk(relaxed = true)
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
    expectedDay shouldEqual actualDay
  }


  @Test
  fun `should validation return FormInvalidStatus when wrong DateTimeFormatter is passed`() {
    // given
    val giveName = "Dummy"
    val givenDate = LocalDate.now()
    val givenDateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

    // when
    val actualFormValidationStatus = viewModel.getFormValidationStatus(giveName, givenDate)

    // then
    expectedDay shouldEqual actualDay
  }

}