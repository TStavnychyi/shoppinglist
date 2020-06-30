package com.stavnychyy.shoppinglist.archivedshoppinglist.di;


import com.stavnychyy.shoppinglist.archivedshoppinglist.presenter.ArchivedShoppingListPresenter;
import com.stavnychyy.shoppinglist.archivedshoppinglist.view.ArchivedShoppingListFragment;

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
