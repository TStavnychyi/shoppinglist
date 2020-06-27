package com.stavnychyy.shoppinglist

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.stavnychyy.shoppinglist.di.*


class ShoppingListApplication : Application(), AddShoppingComponentProvider, EditShoppingListComponentProvider {

    val appComponent: AppComponent by lazy { initializeComponent() }

    override fun provideAddShoppingComponent(): AddShoppingListComponent {
        return appComponent.addShoppingComponentFactory().create()
    }

    override fun provideEditShoppingListComponent(): EditShoppingListComponent {
        return appComponent.addEditShoppingListComponentFactory().create()
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }

    private fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(this)
    }
}
