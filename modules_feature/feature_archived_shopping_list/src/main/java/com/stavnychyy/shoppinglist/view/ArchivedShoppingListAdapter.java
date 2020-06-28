package com.stavnychyy.shoppinglist.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;

import com.stavnychyy.shoppinglist.R;
import com.stavnychyy.shoppinglist.adapter.DefaultListDiffUtilCallback;
import com.stavnychyy.shoppinglist.presenter.ArchivedShoppingListViewEntity;

public class ArchivedShoppingListAdapter
        extends PagedListAdapter<ArchivedShoppingListViewEntity, ArchivedShoppingListViewHolder> {

    protected ArchivedShoppingListAdapter() {
        super(new DefaultListDiffUtilCallback());
    }

    @NonNull
    @Override
    public ArchivedShoppingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.msla_common_shopping_list_adapter_item,
                parent,
                false
        );
        return new ArchivedShoppingListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArchivedShoppingListViewHolder holder, int position) {
        ArchivedShoppingListViewEntity viewEntity = getItem(position);
        if (viewEntity != null) {
            holder.applyViewEntity(viewEntity);
        }
    }
}
