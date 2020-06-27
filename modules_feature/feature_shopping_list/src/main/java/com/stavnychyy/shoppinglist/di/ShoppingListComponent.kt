package com.stavnychyy.shoppinglist.di

import com.stavnychyy.shoppinglist.view.ShoppingListFragment
import dagger.Subcomponent

@Subcomponent(modules = [ShoppingListModule::class])
interface ShoppingListComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ShoppingListComponent
    }

    fun inject(fragment: ShoppingListFragment)
}
