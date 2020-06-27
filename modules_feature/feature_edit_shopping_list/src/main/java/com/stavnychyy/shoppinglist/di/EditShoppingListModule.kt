package com.stavnychyy.shoppinglist.di

import androidx.lifecycle.ViewModel
import com.stavnychyy.shoppinglist.viewmodel.EditShoppingListViewModel
import com.stavnychyy.shoppinglist.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class EditShoppingListModule {
    @Binds
    @IntoMap
    @ViewModelKey(EditShoppingListViewModel::class)
    internal abstract fun provideEditShoppingListViewModel(viewModel: EditShoppingListViewModel): ViewModel
}
