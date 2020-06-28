package com.stavnychyy.shoppinglist.di;


import com.stavnychyy.shoppinglist.model.ArchivedShoppingListRepository;
import com.stavnychyy.shoppinglist.navigation.AppNavigator;
import com.stavnychyy.shoppinglist.presenter.ArchivedShoppingListPresenter;

import dagger.Module;

@Module
class ArchivedShoppingListModule {

    ArchivedShoppingListPresenter providePresenter(
            ArchivedShoppingListPresenter.View view,
            ArchivedShoppingListRepository repository,
            AppNavigator appNavigator
    ) {
        return new ArchivedShoppingListPresenter(view, repository, appNavigator);
    }
}
