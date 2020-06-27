package com.stavnychyy.shoppinglist.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.stavnychyy.shoppinglist.PCLShoppingListId
import com.stavnychyy.shoppinglist.R
import com.stavnychyy.shoppinglist.ShoppingListId
import com.stavnychyy.shoppinglist.di.EditShoppingListComponentProvider
import com.stavnychyy.shoppinglist.extensions.linearVertical
import com.stavnychyy.shoppinglist.fragment.BaseFragment
import com.stavnychyy.shoppinglist.view.adapter.ShoppingListItemsAdapter
import com.stavnychyy.shoppinglist.viewmodel.EditShoppingListViewModel
import kotlinx.android.synthetic.main.msla_edit_shopping_list_fragment.*
import javax.inject.Inject


class EditShoppingListFragment : BaseFragment(R.layout.msla_edit_shopping_list_fragment) {

    companion object {
        private const val SHOPPING_LIST_ID_ARG = "SHOPPING_LIST_ID_ARG"

        fun createBundle(shoppingListId: ShoppingListId) = Bundle().apply {
            putParcelable(SHOPPING_LIST_ID_ARG, PCLShoppingListId(shoppingListId))
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val shoppingListId: ShoppingListId by lazy {
        arguments?.getParcelable<PCLShoppingListId>(SHOPPING_LIST_ID_ARG)?.toDomain()
            ?: throw IllegalArgumentException("Shopping list ID required.")
    }

    private val shoppingListItemsAdapter = ShoppingListItemsAdapter()

    private val viewModel by lazy {
        requireActivity().viewModels<EditShoppingListViewModel> { viewModelFactory }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as EditShoppingListComponentProvider)
            .provideEditShoppingListComponent()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.value.loadShoppingList(shoppingListId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        view_add_item_button.setOnClickListener { openAddShoppingListItemBottomSheet() }

        with(viewModel.value) {
            shoppingListItemsLiveData.observe(viewLifecycleOwner, Observer {
                shoppingListItemsAdapter.setItems(it)
            })

            shoppingListViewEntity.observe(viewLifecycleOwner, Observer {
                changeToolbarTitle(it.title)
                if (it.items.isNotEmpty()) {
                    shoppingListItemsAdapter.setItems(it.items)
                }
            })
        }

        requireActivity().onBackPressedDispatcher.addCallback {
            viewModel.value.saveShoppingList()
        }
    }

    private fun initAdapter() {
        view_list_items.apply {
            linearVertical()
            adapter = shoppingListItemsAdapter
        }
    }

    private fun openAddShoppingListItemBottomSheet() {
        findNavController().navigate(R.id.action_editShoppingListFragment_to_addShoppingListItemBottomSheet)
    }
}
