package com.stavnychyy.shoppinglist.di

import androidx.lifecycle.ViewModel
import com.stavnychyy.shoppinglist.viewmodel.AddShoppingListViewModel
import com.stavnychyy.shoppinglist.viewmodel.ViewModelKey
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
