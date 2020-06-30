package com.stavnychyy.shoppinglist.archivedshoppinglist.presenter;


import com.stavnychyy.shoppinglist.domain.ShoppingList;
import com.stavnychyy.shoppinglist.archivedshoppinglist.view.adapter.ArchivedShoppingListViewHolder;

import org.threeten.bp.format.DateTimeFormatter;

import static com.stavnychyy.shoppinglist.common.CommonConstKt.SHOPPING_LIST_DATE_FORMAT;

public class ArchivedShoppingListViewEntity {

    private String title;
    private String purchaseDate;
    private ShoppingList shoppingList;
    private ArchivedShoppingListViewHolder.ArchiveShoppingListClickListeners listeners;

    public ArchivedShoppingListViewEntity(
            String title,
            String purchaseDate,
            ShoppingList shoppingList,
            ArchivedShoppingListViewHolder.ArchiveShoppingListClickListeners listeners
    ) {
        this.title = title;
        this.purchaseDate = purchaseDate;
        this.shoppingList = shoppingList;
        this.listeners = listeners;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }


    public String getTitle() {
        return title;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public ArchivedShoppingListViewHolder.ArchiveShoppingListClickListeners getListeners() {
        return listeners;
    }

    public static ArchivedShoppingListViewEntity create(
            ShoppingList shoppingList,
            ArchivedShoppingListViewHolder.ArchiveShoppingListClickListeners listeners
    ) {
        return new ArchivedShoppingListViewEntity(
                shoppingList.getName(),
                shoppingList.getPurchaseDate().format(DateTimeFormatter.ofPattern(SHOPPING_LIST_DATE_FORMAT)),
                shoppingList,
                listeners
        );
    }
}
