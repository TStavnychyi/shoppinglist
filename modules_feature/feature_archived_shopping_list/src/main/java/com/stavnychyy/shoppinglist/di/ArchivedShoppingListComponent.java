package com.stavnychyy.shoppinglist.di;


import com.stavnychyy.shoppinglist.presenter.ArchivedShoppingListPresenter;
import com.stavnychyy.shoppinglist.view.ArchivedShoppingListFragment;

import dagger.BindsInstance;
import dagger.Subcomponent;

@Subcomponent(modules = ArchivedShoppingListModule.class)
public interface ArchivedShoppingListComponent {

    @Subcomponent.Factory
    interface Factory {
        ArchivedShoppingListComponent create(@BindsInstance ArchivedShoppingListPresenter.View view);
    }

    void inject(ArchivedShoppingListFragment fragment);
}
