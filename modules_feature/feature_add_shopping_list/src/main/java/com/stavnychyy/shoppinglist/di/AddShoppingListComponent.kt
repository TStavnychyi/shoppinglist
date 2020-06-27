package com.stavnychyy.shoppinglist.di

import com.stavnychyy.shoppinglist.view.AddShoppingListFragment
import dagger.Subcomponent


@Subcomponent(modules = [AddShoppingListModule::class])
interface AddShoppingListComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): AddShoppingListComponent
    }

    fun inject(fragment: AddShoppingListFragment)
}
