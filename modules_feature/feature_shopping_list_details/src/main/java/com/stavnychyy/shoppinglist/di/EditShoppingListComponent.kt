package com.stavnychyy.shoppinglist.di

import com.stavnychyy.shoppinglist.view.AddShoppingListItemBottomSheet
import com.stavnychyy.shoppinglist.view.ShoppingListDetailsFragment
import dagger.BindsInstance
import dagger.Subcomponent


@Subcomponent(modules = [EditShoppingListModule::class])
interface EditShoppingListComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance fragment: ShoppingListDetailsFragment): EditShoppingListComponent
    }

    fun inject(fragment: ShoppingListDetailsFragment)
    fun inject(fragment: AddShoppingListItemBottomSheet)
}
