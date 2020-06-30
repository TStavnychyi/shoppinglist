package com.stavnychyy.shoppinglist.shoppinglistdetails.di

import com.stavnychyy.shoppinglist.shoppinglistdetails.view.AddShoppingListItemBottomSheet
import com.stavnychyy.shoppinglist.shoppinglistdetails.view.ShoppingListDetailsFragment
import dagger.BindsInstance
import dagger.Subcomponent


@Subcomponent(modules = [ShoppingListDetailsModule::class])
interface ShoppingListDetailsComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance fragment: ShoppingListDetailsFragment): ShoppingListDetailsComponent
    }

    fun inject(fragment: ShoppingListDetailsFragment)
    fun inject(fragment: AddShoppingListItemBottomSheet)
}
