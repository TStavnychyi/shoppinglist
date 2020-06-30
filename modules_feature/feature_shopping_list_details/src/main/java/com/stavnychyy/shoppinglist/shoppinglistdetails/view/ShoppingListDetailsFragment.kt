package com.stavnychyy.shoppinglist.shoppinglistdetails.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.stavnychyy.shoppinglist.common.extensions.changeToolbarTitle
import com.stavnychyy.shoppinglist.common.extensions.linearVertical
import com.stavnychyy.shoppinglist.common.extensions.visibleOrGone
import com.stavnychyy.shoppinglist.domain.ShoppingListId
import com.stavnychyy.shoppinglist.parcelabledomain.PCLShoppingListId
import com.stavnychyy.shoppinglist.shoppinglistdetails.R
import com.stavnychyy.shoppinglist.shoppinglistdetails.di.ShoppingListDetailsComponentFactoryProvider
import com.stavnychyy.shoppinglist.shoppinglistdetails.model.AddingShoppingListItemResult
import com.stavnychyy.shoppinglist.shoppinglistdetails.view.adapter.ShoppingListItemsAdapter
import com.stavnychyy.shoppinglist.shoppinglistdetails.viewmodel.ShoppingListDetailsViewModel
import kotlinx.android.synthetic.main.msla_shopping_list_details_fragment.*
import javax.inject.Inject
import com.stavnychyy.shoppinglist.common.R as commonR

class ShoppingListDetailsFragment : Fragment() {

  companion object {
    private const val SHOPPING_LIST_ID_ARG = "SHOPPING_LIST_ID_ARG"
    private const val SHOPPING_LIST_TITLE_ARG = "SHOPPING_LIST_TITLE_ARG"
    private const val IS_SHOPPING_LIST_IN_READ_MODE_ARG = "IS_SHOPPING_LIST_IN_READ_MODE_ARG"

    internal const val ADDED_SHOPPING_LIST_ITEM = "ADDED_SHOPPING_LIST_ITEM"

    fun createBundle(
      shoppingListId: ShoppingListId,
      shoppingListTitle: String,
      isInReadMode: Boolean
    ) = Bundle().apply {
      putParcelable(
        SHOPPING_LIST_ID_ARG,
        PCLShoppingListId(shoppingListId))
      putString(
        SHOPPING_LIST_TITLE_ARG, shoppingListTitle)
      putBoolean(
        IS_SHOPPING_LIST_IN_READ_MODE_ARG, isInReadMode)
    }
  }

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  private val shoppingListId: ShoppingListId by lazy {
    arguments?.getParcelable<PCLShoppingListId>(
      SHOPPING_LIST_ID_ARG)?.toDomain()
      ?: throw IllegalArgumentException("Shopping list ID required.")
  }

  private val isInReadMode: Boolean by lazy {
    arguments?.getBoolean(
      IS_SHOPPING_LIST_IN_READ_MODE_ARG)
      ?: throw IllegalArgumentException("Shopping list read mode status required.")
  }

  private val shoppingListTitle: String by lazy {
    arguments?.getString(
      SHOPPING_LIST_TITLE_ARG)
      ?: throw IllegalArgumentException("Shopping list title required.")
  }

  private lateinit var shoppingListItemsAdapter: ShoppingListItemsAdapter

  private val viewModel by viewModels<ShoppingListDetailsViewModel> { viewModelFactory }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    (requireActivity().application as ShoppingListDetailsComponentFactoryProvider)
      .provideEditShoppingListComponentFactory()
      .create(this)
      .inject(this)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel.loadShoppingList(shoppingListId)
    shoppingListItemsAdapter = ShoppingListItemsAdapter(
      isInReadMode)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.msla_shopping_list_details_fragment, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    changeToolbarTitle(shoppingListTitle)
    changeEmptyScreenSubtitle()
    initAdapter()
    view_add_item_button.apply {
      visibleOrGone(!isInReadMode)
      if (!isInReadMode) {
        setOnClickListener { openAddShoppingListItemBottomSheet() }
      }
    }
    with(viewModel) {
      shoppingListItemsLiveData.observe(viewLifecycleOwner, Observer {
        view_empty_screen.visibleOrGone(it.isEmpty())
        view_list_items.visibleOrGone(it.isNotEmpty())
        shoppingListItemsAdapter.submitList(it)
      })

      findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<AddingShoppingListItemResult>(
        ADDED_SHOPPING_LIST_ITEM
      )?.observe(viewLifecycleOwner, Observer {
        addShoppingListItem(it.title, it.subtitle)
      })
    }
  }

  override fun onPause() {
    super.onPause()
    viewModel.saveChanges()
  }

  private fun initAdapter() {
    view_list_items.apply {
      linearVertical()
      adapter = shoppingListItemsAdapter
    }
  }

  private fun changeEmptyScreenSubtitle() {
    val subtitle = requireView().findViewById<TextView>(commonR.id.view_subtitle)
    subtitle.text = getText(R.string.msla_shopping_list_details_empty_screen_title)
  }

  private fun openAddShoppingListItemBottomSheet() {
    findNavController().navigate(
      ShoppingListDetailsFragmentDirections.actionShoppingListDetailsFragmentToAddShoppingListItemBottomSheet(
        null,
        null
      )
    )
  }
}
