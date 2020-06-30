package com.stavnychyy.shoppinglist.shoppinglistdetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.stavnychyy.shoppinglist.common.extensions.getString
import com.stavnychyy.shoppinglist.common.extensions.inflateNoAttach
import com.stavnychyy.shoppinglist.common.extensions.placeCursorToEnd
import com.stavnychyy.shoppinglist.common.fragment.BaseBottomSheetDialogFragment
import com.stavnychyy.shoppinglist.shoppinglistdetails.R
import com.stavnychyy.shoppinglist.shoppinglistdetails.model.AddingShoppingListItemResult
import com.stavnychyy.shoppinglist.shoppinglistdetails.view.ShoppingListDetailsFragment.Companion.SHOPPING_LIST_ITEM
import kotlinx.android.synthetic.main.msla_shopping_list_details_add_item_bottom_sheet_fragment.*

class ShoppingListItemBottomSheet : BaseBottomSheetDialogFragment() {

  private val args: ShoppingListItemBottomSheetArgs by navArgs()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflateNoAttach(R.layout.msla_shopping_list_details_add_item_bottom_sheet_fragment, null)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    args.title?.let {
      view_item_name.apply {
        setText(it)
        placeCursorToEnd()
      }
    }
    args.notes?.let { view_item_additional_info.setText(it) }

    view_item_name.doAfterTextChanged {
      view_add_item_button.isEnabled = !it.isNullOrEmpty()
    }
    view_item_additional_info.doAfterTextChanged {
      view_add_item_button.isEnabled = view_item_name.text.isNotEmpty()
    }

    view_add_item_button.setOnClickListener { createNewItem() }
  }

  private fun createNewItem() {
    val listItem = AddingShoppingListItemResult(
      args.shoppingListId,
      view_item_name.getString(),
      view_item_additional_info.getString()
    )
    findNavController().previousBackStackEntry?.savedStateHandle?.set(SHOPPING_LIST_ITEM, listItem)
    dismiss()
  }
}
