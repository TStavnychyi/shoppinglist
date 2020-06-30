package com.stavnychyy.shoppinglist.addshoppinglist.di

import androidx.lifecycle.ViewModel
import com.stavnychyy.shoppinglist.addshoppinglist.viewmodel.AddShoppingListViewModel
import com.stavnychyy.shoppinglist.common.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class AddShoppingListModule {

    @Binds
    @IntoMap
    @ViewModelKey(AddShoppingListViewModel::class)
    internal abstract fun provideAddShoppingListViewModel(viewModel: AddShoppingListViewModel): ViewModel
}
