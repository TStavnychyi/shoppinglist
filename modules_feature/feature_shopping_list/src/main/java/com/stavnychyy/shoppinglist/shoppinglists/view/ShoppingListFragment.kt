package com.stavnychyy.shoppinglist.shoppinglists.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.SimpleItemAnimator
import com.stavnychyy.shoppinglist.shoppinglists.di.ShoppingListComponentProvider
import com.stavnychyy.shoppinglist.common.extensions.changeToolbarTitle
import com.stavnychyy.shoppinglist.common.extensions.disableItemAnimations
import com.stavnychyy.shoppinglist.common.extensions.linearVertical
import com.stavnychyy.shoppinglist.common.extensions.visibleOrGone
import com.stavnychyy.shoppinglist.common.navigation.AppNavigator
import com.stavnychyy.shoppinglist.shoppinglists.R
import com.stavnychyy.shoppinglist.shoppinglists.viewmodel.ShoppingListViewModel
import dagger.Lazy
import kotlinx.android.synthetic.main.msla_shopping_list_fragment.*
import javax.inject.Inject

class ShoppingListFragment : Fragment() {

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

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.msla_shopping_list_fragment, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    shoppingListViewModel.setAppNavigator(appNavigator.get())
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
      disableItemAnimations()
      linearVertical()
      adapter = shoppingListAdapter
    }
  }

  private fun openAddShoppingListScreen() {
    appNavigator.get().openAddShoppingListScreen()
  }
}
