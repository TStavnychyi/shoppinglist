package com.stavnychyy.shoppinglist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.stavnychyy.shoppinglist.R
import com.stavnychyy.shoppinglist.extensions.getString
import com.stavnychyy.shoppinglist.extensions.inflateNoAttach
import com.stavnychyy.shoppinglist.extensions.placeCursorToEnd
import com.stavnychyy.shoppinglist.fragment.BaseBottomSheetDialogFragment
import com.stavnychyy.shoppinglist.view.ShoppingListDetailsFragment.Companion.ADDED_SHOPPING_LIST_ITEM
import kotlinx.android.synthetic.main.msla_edit_shopping_list_add_item_bottom_sheet_fragment.*


class AddShoppingListItemBottomSheet : BaseBottomSheetDialogFragment() {


    private val args: AddShoppingListItemBottomSheetArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflateNoAttach(R.layout.msla_edit_shopping_list_add_item_bottom_sheet_fragment, null)
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

        view_add_item_button.setOnClickListener { createNewItem() }
    }

    private fun createNewItem() {
        val listItem = ShoppingListItemResult(view_item_name.getString(), view_item_additional_info.getString())
        findNavController().previousBackStackEntry?.savedStateHandle?.set(ADDED_SHOPPING_LIST_ITEM, listItem)
        dismiss()
    }
}
