package com.stavnychyy.shoppinglist.shoppinglists.di

import android.content.Context
import com.stavnychyy.shoppinglist.addshoppinglist.di.AddShoppingListComponent
import com.stavnychyy.shoppinglist.shoppinglistdetails.di.ShoppingListDetailsComponent
import com.stavnychyy.shoppinglist.shoppinglists.di.activity.ActivityComponent
import com.stavnychyy.shoppinglist.common.viewmodel.ViewModelBuilderModule
import com.stavnychyy.shoppinglist.storage.di.DatabaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ViewModelBuilderModule::class,
        SubcomponentsModule::class,
        DatabaseModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun activityComponentFactory(): ActivityComponent.Factory
    fun addShoppingComponentFactory(): AddShoppingListComponent.Factory
    fun editShoppingListComponentFactory(): ShoppingListDetailsComponent.Factory
}

@Module(
    subcomponents = [
        ActivityComponent::class,
        AddShoppingListComponent::class,
        ShoppingListDetailsComponent::class
    ]
)
private object SubcomponentsModule
