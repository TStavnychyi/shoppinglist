package com.stavnychyy.shoppinglist.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.stavnychyy.shoppinglist.R
import com.stavnychyy.shoppinglist.di.ShoppingListComponentProvider
import com.stavnychyy.shoppinglist.extensions.linearVertical
import com.stavnychyy.shoppinglist.extensions.visibleOrGone
import com.stavnychyy.shoppinglist.fragment.BaseFragment
import com.stavnychyy.shoppinglist.navigation.AppNavigator
import com.stavnychyy.shoppinglist.viewmodel.ShoppingListViewModel
import dagger.Lazy
import kotlinx.android.synthetic.main.msla_shopping_list_fragment.*
import javax.inject.Inject


class ShoppingListFragment : BaseFragment(R.layout.msla_shopping_list_fragment) {

    @Inject
    lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>

    @Inject
    lateinit var appNavigator: Lazy<AppNavigator>

    private val shoppingListAdapter = ShoppingListAdapter()

    private val shoppingListViewModel by viewModels<ShoppingListViewModel> { viewModelFactory.get() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as ShoppingListComponentProvider)
            .provideShoppingListComponent()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shoppingListViewModel.getShoppingLists()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeToolbarTitle(resources.getString(R.string.msla_shopping_list_toolbar_title))
        initShoppingList()

        view_add_shopping_list_button.setOnClickListener { openAddShoppingListScreen() }

        shoppingListViewModel.shoppingListViewEntity.observe(viewLifecycleOwner, Observer {
            showContentOrEmptyScreen(it.isNotEmpty())
            shoppingListAdapter.submitList(it)
        })
    }

    private fun showContentOrEmptyScreen(isContentAvailable: Boolean) {
        view_shopping_list.visibleOrGone(isContentAvailable)
        view_empty_list.visibleOrGone(!isContentAvailable)
    }

    private fun initShoppingList() {
        view_shopping_list.apply {
            linearVertical()
            adapter = shoppingListAdapter
        }
    }

    private fun openAddShoppingListScreen() {
        appNavigator.get().openAddShoppingListScreen()
    }
}