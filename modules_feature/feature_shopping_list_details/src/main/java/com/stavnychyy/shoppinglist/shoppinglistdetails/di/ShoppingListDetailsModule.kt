package com.stavnychyy.shoppinglist.shoppinglistdetails.di

import androidx.lifecycle.ViewModel
import com.stavnychyy.shoppinglist.shoppinglistdetails.viewmodel.ShoppingListDetailsViewModel
import com.stavnychyy.shoppinglist.common.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ShoppingListDetailsModule {
    @Binds
    @IntoMap
    @ViewModelKey(
      ShoppingListDetailsViewModel::class)
    internal abstract fun provideEditShoppingListViewModel(viewModel: ShoppingListDetailsViewModel): ViewModel
}
