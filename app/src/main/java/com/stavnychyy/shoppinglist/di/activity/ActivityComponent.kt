package com.stavnychyy.shoppinglist.di.activity

import com.stavnychyy.shoppinglist.archivedshoppinglist.di.ArchivedShoppingListComponent
import com.stavnychyy.shoppinglist.shoppinglists.di.ShoppingListComponent
import com.stavnychyy.shoppinglist.view.MainActivity
import dagger.BindsInstance
import dagger.Module
import dagger.Subcomponent


@ActivityScope
@Subcomponent(modules = [SubcomponentsModule::class, ActivityModule::class])
interface ActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: MainActivity): ActivityComponent
    }

    fun inject(activity: MainActivity)

    fun shoppingListComponentFactory(): ShoppingListComponent.Factory
    fun archivedShoppingListComponentFactory(): ArchivedShoppingListComponent.Factory
}

@Module(
    subcomponents = [ShoppingListComponent::class, ArchivedShoppingListComponent::class]
)
private object SubcomponentsModule
