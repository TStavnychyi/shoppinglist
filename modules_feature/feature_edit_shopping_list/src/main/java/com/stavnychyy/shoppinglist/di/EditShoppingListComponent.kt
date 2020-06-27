package com.stavnychyy.shoppinglist.di

import com.stavnychyy.shoppinglist.view.AddShoppingListItemBottomSheet
import com.stavnychyy.shoppinglist.view.EditShoppingListFragment
import dagger.Subcomponent


@EditShoppingListScope
@Subcomponent(modules = [EditShoppingListModule::class])
interface EditShoppingListComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): EditShoppingListComponent
    }

    fun inject(fragment: EditShoppingListFragment)
    fun inject(fragment: AddShoppingListItemBottomSheet)
}
