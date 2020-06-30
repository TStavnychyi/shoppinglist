package com.stavnychyy.shoppinglist.addshoppinglist.di

import com.stavnychyy.shoppinglist.addshoppinglist.view.AddShoppingListFragment
import dagger.Subcomponent


@Subcomponent(modules = [AddShoppingListModule::class])
interface AddShoppingListComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): AddShoppingListComponent
    }

    fun inject(fragment: AddShoppingListFragment)
}
