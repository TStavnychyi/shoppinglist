package com.stavnychyy.shoppinglist.di

import android.content.Context
import com.stavnychyy.shoppinglist.di.activity.ActivityComponent
import com.stavnychyy.shoppinglist.viewmodel.ViewModelBuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
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
    fun addEditShoppingListComponentFactory(): EditShoppingListComponent.Factory
}

@Module(
    subcomponents = [
        ActivityComponent::class,
        AddShoppingListComponent::class,
        EditShoppingListComponent::class
    ]
)
private object SubcomponentsModule
