package com.stavnychyy.shoppinglist.archivedshoppinglist.di;

import com.stavnychyy.shoppinglist.archivedshoppinglist.model.ArchivedShoppingListRepository;
import com.stavnychyy.shoppinglist.archivedshoppinglist.presenter.ArchivedShoppingListPresenter;
import dagger.Module;

@Module
class ArchivedShoppingListModule {

  ArchivedShoppingListPresenter providePresenter(
    ArchivedShoppingListPresenter.View view,
    ArchivedShoppingListRepository repository
  ) {
    return new ArchivedShoppingListPresenter(view, repository);
  }
}
