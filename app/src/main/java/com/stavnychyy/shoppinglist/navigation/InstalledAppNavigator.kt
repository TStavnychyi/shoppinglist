package com.stavnychyy.shoppinglist.navigation

import androidx.navigation.NavController
import com.stavnychyy.shoppinglist.R
import com.stavnychyy.shoppinglist.common.navigation.AppNavigator
import com.stavnychyy.shoppinglist.domain.ShoppingListId
import com.stavnychyy.shoppinglist.shoppinglistdetails.view.ShoppingListDetailsFragment


class InstalledAppNavigator(private val navController: NavController) : AppNavigator {

    override fun openAddShoppingListScreen() {
        navController.navigate(R.id.add_shopping_list_fragment)
    }

    override fun opeShoppingListDetailsScreen(
        shoppingListId: ShoppingListId,
        title: String,
        inReadMode: Boolean
    ) {
        navController.navigate(
            R.id.msla_shopping_list_details_navigation,
            ShoppingListDetailsFragment.createBundle(shoppingListId, title, inReadMode)
        )
    }
}
