package com.stavnychyy.shoppinglist.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.stavnychyy.shoppinglist.R
import com.stavnychyy.shoppinglist.di.EditShoppingListComponentProvider
import com.stavnychyy.shoppinglist.extensions.getString
import com.stavnychyy.shoppinglist.extensions.inflateNoAttach
import com.stavnychyy.shoppinglist.fragment.BaseBottomSheetDialogFragment
import com.stavnychyy.shoppinglist.viewmodel.EditShoppingListViewModel
import kotlinx.android.synthetic.main.msla_edit_shopping_list_add_item_bottom_sheet_fragment.*
import javax.inject.Inject


class AddShoppingListItemBottomSheet : BaseBottomSheetDialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        requireActivity().viewModels<EditShoppingListViewModel> { viewModelFactory }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as EditShoppingListComponentProvider)
            .provideEditShoppingListComponent()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflateNoAttach(R.layout.msla_edit_shopping_list_add_item_bottom_sheet_fragment, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view_item_name.doAfterTextChanged {
            view_add_item_button.isEnabled = !it.isNullOrEmpty()
        }

        view_add_item_button.setOnClickListener { createNewItem() }
    }

    private fun createNewItem() {
        viewModel.value.addShoppingListItem(view_item_name.getString(), view_item_additional_info.getString())
        dismiss()
    }
}
