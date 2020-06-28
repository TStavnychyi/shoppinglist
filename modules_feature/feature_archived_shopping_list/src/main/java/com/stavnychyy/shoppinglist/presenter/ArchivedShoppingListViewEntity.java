package com.stavnychyy.shoppinglist.presenter;


import com.stavnychyy.shoppinglist.ShoppingList;
import com.stavnychyy.shoppinglist.view.ArchivedShoppingListViewHolder;

import org.threeten.bp.format.DateTimeFormatter;

import static com.stavnychyy.shoppinglist.CommonConstKt.SHOPPING_LIST_DATE_FORMAT;

public class ArchivedShoppingListViewEntity {

    private String title;
    private String purchaseDate;
    private String status;
    private ShoppingList shoppingList;
    private ArchivedShoppingListViewHolder.ArchiveShoppingListClickListeners listeners;

    public ArchivedShoppingListViewEntity(
            String title,
            String purchaseDate,
            String status,
            ShoppingList shoppingList,
            ArchivedShoppingListViewHolder.ArchiveShoppingListClickListeners listeners
    ) {
        this.title = title;
        this.purchaseDate = purchaseDate;
        this.status = status;
        this.shoppingList = shoppingList;
        this.listeners = listeners;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public String getStatus() {
        return status;
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
                "0/1",
                shoppingList,
                listeners
        );
    }
}
