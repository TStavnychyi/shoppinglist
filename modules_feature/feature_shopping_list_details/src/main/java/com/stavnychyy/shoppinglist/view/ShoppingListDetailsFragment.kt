package com.stavnychyy.shoppinglist.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.stavnychyy.shoppinglist.PCLShoppingListId
import com.stavnychyy.shoppinglist.R
import com.stavnychyy.shoppinglist.ShoppingListId
import com.stavnychyy.shoppinglist.di.EditShoppingListComponentFactoryProvider
import com.stavnychyy.shoppinglist.extensions.linearVertical
import com.stavnychyy.shoppinglist.extensions.visibleOrGone
import com.stavnychyy.shoppinglist.fragment.BaseFragment
import com.stavnychyy.shoppinglist.view.adapter.ShoppingListItemsAdapter
import com.stavnychyy.shoppinglist.viewmodel.ShoppingListDetailsViewModel
import kotlinx.android.synthetic.main.msla_edit_shopping_list_fragment.*
import javax.inject.Inject


class ShoppingListDetailsFragment : BaseFragment(R.layout.msla_edit_shopping_list_fragment) {

    companion object {
        private const val SHOPPING_LIST_ID_ARG = "SHOPPING_LIST_ID_ARG"
        private const val IS_SHOPPING_LIST_IN_READ_MODE_ARG = "IS_SHOPPING_LIST_IN_READ_MODE_ARG"

        internal const val ADDED_SHOPPING_LIST_ITEM = "ADDED_SHOPPING_LIST_ITEM"

        fun createBundle(shoppingListId: ShoppingListId, isInReadMode: Boolean) = Bundle().apply {
            putParcelable(SHOPPING_LIST_ID_ARG, PCLShoppingListId(shoppingListId))
            putBoolean(IS_SHOPPING_LIST_IN_READ_MODE_ARG, isInReadMode)
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val shoppingListId: ShoppingListId by lazy {
        arguments?.getParcelable<PCLShoppingListId>(SHOPPING_LIST_ID_ARG)?.toDomain()
            ?: throw IllegalArgumentException("Shopping list ID required.")
    }

    private val isInReadMode: Boolean by lazy {
        arguments?.getBoolean(IS_SHOPPING_LIST_IN_READ_MODE_ARG)
            ?: throw IllegalArgumentException("Shopping list read mode status required.")
    }

    private lateinit var shoppingListItemsAdapter: ShoppingListItemsAdapter

    private val viewModel by viewModels<ShoppingListDetailsViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as EditShoppingListComponentFactoryProvider)
            .provideEditShoppingListComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadShoppingList(shoppingListId)
        shoppingListItemsAdapter = ShoppingListItemsAdapter(isInReadMode)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        view_add_item_button.apply {
            visibleOrGone(!isInReadMode)
            if (!isInReadMode) {
                setOnClickListener { openAddShoppingListItemBottomSheet() }
            }
        }
        with(viewModel) {
            changeFragmentTitleLiveEvent.observe(viewLifecycleOwner) {
                changeToolbarTitle(it)
            }

            shoppingListItemsLiveData.observe(viewLifecycleOwner, Observer {
                shoppingListItemsAdapter.setItems(it)
            })

            findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<ShoppingListItemResult>(
                ADDED_SHOPPING_LIST_ITEM
            )?.observe(viewLifecycleOwner, Observer {
                addShoppingListItem(it.title, it.subtitle)
            })
        }
    }

    override fun onBackPressed() {
        viewModel.saveShoppingList()
    }

    private fun initAdapter() {
        view_list_items.apply {
            linearVertical()
            adapter = shoppingListItemsAdapter
        }
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
