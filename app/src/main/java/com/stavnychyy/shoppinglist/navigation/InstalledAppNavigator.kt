package com.stavnychyy.shoppinglist.navigation

import androidx.navigation.NavController
import com.stavnychyy.shoppinglist.PCLShoppingListId
import com.stavnychyy.shoppinglist.R
import com.stavnychyy.shoppinglist.ShoppingListId
import com.stavnychyy.shoppinglist.view.EditShoppingListFragment
import com.stavnychyy.shoppinglist.view.EditShoppingListFragmentArgs
import com.stavnychyy.shoppinglist.view.ShoppingListFragmentDirections


class InstalledAppNavigator(private val navController: NavController) : AppNavigator {

    override fun openAddShoppingListScreen() {
        navController.navigate(R.id.open_add_shopping_list_screen)
    }

    override fun openEditShoppingListScreen(shoppingListId: ShoppingListId) {
        navController.navigate(R.id.action_shopping_list_fragment_to_msla_edit_shopping_list_navigation, EditShoppingListFragment.createBundle(shoppingListId))
    }
}
