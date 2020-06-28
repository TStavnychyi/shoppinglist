package com.stavnychyy.shoppinglist.navigation

import androidx.navigation.NavController
import com.stavnychyy.shoppinglist.R
import com.stavnychyy.shoppinglist.ShoppingListId
import com.stavnychyy.shoppinglist.view.ShoppingListDetailsFragment


class InstalledAppNavigator(private val navController: NavController) : AppNavigator {

    override fun openAddShoppingListScreen() {
        navController.navigate(R.id.open_add_shopping_list_screen)
    }

    override fun opeShoppingListDetailsScreen(shoppingListId: ShoppingListId, inReadMode: Boolean) {
        navController.navigate(
            R.id.msla_shopping_list_details_navigation,
            ShoppingListDetailsFragment.createBundle(shoppingListId, inReadMode)
        )
    }
}
