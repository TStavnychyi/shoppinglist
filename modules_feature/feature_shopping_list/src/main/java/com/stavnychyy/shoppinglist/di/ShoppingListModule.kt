package com.stavnychyy.shoppinglist.di

import androidx.lifecycle.ViewModel
import com.stavnychyy.shoppinglist.viewmodel.ShoppingListViewModel
import com.stavnychyy.shoppinglist.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ShoppingListModule {

    @Binds
    @IntoMap
    @ViewModelKey(ShoppingListViewModel::class)
    internal abstract fun shoppingListViewModel(viewModel: ShoppingListViewModel): ViewModel
}
