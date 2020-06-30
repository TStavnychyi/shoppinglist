package com.stavnychyy.shoppinglist

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.stavnychyy.shoppinglist.addshoppinglist.di.AddShoppingComponentProvider
import com.stavnychyy.shoppinglist.addshoppinglist.di.AddShoppingListComponent
import com.stavnychyy.shoppinglist.shoppinglistdetails.di.ShoppingListDetailsComponent
import com.stavnychyy.shoppinglist.shoppinglistdetails.di.ShoppingListDetailsComponentFactoryProvider
import com.stavnychyy.shoppinglist.shoppinglists.di.*


class ShoppingListApplication : Application(), AddShoppingComponentProvider,
  ShoppingListDetailsComponentFactoryProvider {

    val appComponent: AppComponent by lazy { initializeComponent() }

    override fun provideAddShoppingComponent(): AddShoppingListComponent {
        return appComponent.addShoppingComponentFactory().create()
    }

    override fun provideEditShoppingListComponentFactory(): ShoppingListDetailsComponent.Factory {
        return appComponent.editShoppingListComponentFactory()
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }

    private fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(this)
    }
}
