package com.stavnychyy.shoppinglist.di.activity

import com.stavnychyy.shoppinglist.di.ShoppingListComponent
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
}

@Module(subcomponents = [ShoppingListComponent::class])
private object SubcomponentsModule
