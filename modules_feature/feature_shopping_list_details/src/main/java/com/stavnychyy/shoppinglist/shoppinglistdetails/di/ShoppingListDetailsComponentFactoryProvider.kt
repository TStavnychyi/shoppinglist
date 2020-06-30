package com.stavnychyy.shoppinglist.shoppinglistdetails.di

import com.stavnychyy.shoppinglist.shoppinglistdetails.di.ShoppingListDetailsComponent

interface ShoppingListDetailsComponentFactoryProvider {
    fun provideEditShoppingListComponentFactory(): ShoppingListDetailsComponent.Factory
}
