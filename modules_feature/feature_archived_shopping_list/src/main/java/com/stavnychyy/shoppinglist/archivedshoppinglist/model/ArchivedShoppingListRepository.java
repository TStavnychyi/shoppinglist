package com.stavnychyy.shoppinglist.archivedshoppinglist.model;

import androidx.paging.DataSource;

import com.stavnychyy.shoppinglist.domain.ShoppingList;
import com.stavnychyy.shoppinglist.storage.shopping_list.ShoppingListDao;

import javax.inject.Inject;

import io.reactivex.Completable;

public class ArchivedShoppingListRepository {

    private final ShoppingListDao shoppingListDao;

    @Inject
    public ArchivedShoppingListRepository(ShoppingListDao shoppingListDao) {
        this.shoppingListDao = shoppingListDao;
    }

    public DataSource.Factory<Integer, ShoppingList> getArchivedShoppingList() {
        return shoppingListDao.getArchivedShoppingLists();
    }

    public Completable deleteShoppingListWithItems(ShoppingList shoppingList) {
        return shoppingListDao.deleteShoppingListWithItems(shoppingList);
    }

    public Completable updateShoppingList(ShoppingList shoppingList) {
        return shoppingListDao.updateShoppingList(shoppingList);
    }
}
