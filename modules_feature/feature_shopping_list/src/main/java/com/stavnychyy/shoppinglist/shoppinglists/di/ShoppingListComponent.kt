package com.stavnychyy.shoppinglist.shoppinglists.di

import com.stavnychyy.shoppinglist.shoppinglists.view.ShoppingListFragment
import dagger.Subcomponent

@Subcomponent(modules = [ShoppingListModule::class])
interface ShoppingListComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ShoppingListComponent
    }

    fun inject(fragment: ShoppingListFragment)
}
