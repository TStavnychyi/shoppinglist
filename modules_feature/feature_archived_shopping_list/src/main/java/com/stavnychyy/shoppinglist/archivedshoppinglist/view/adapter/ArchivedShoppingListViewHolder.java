package com.stavnychyy.shoppinglist.archivedshoppinglist.view.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.stavnychyy.shoppinglist.domain.ShoppingList;
import com.stavnychyy.shoppinglist.domain.ShoppingListId;
import com.stavnychyy.shoppinglist.archivedshoppinglist.R;
import com.stavnychyy.shoppinglist.archivedshoppinglist.presenter.ArchivedShoppingListViewEntity;

public class ArchivedShoppingListViewHolder extends RecyclerView.ViewHolder {

  public ArchivedShoppingListViewHolder(@NonNull View itemView) {
    super(itemView);
  }

  private TextView viewTitle = itemView.findViewById(R.id.view_title);

  private TextView viewCreatedDate = itemView.findViewById(R.id.view_created_date);

  private TextView viewCheckedItemsCount = itemView.findViewById(R.id.view_checked_items_count);

  private ImageView viewOptions = itemView.findViewById(R.id.view_options);

  public void applyViewEntity(ArchivedShoppingListViewEntity viewEntity) {
    viewTitle.setText(viewEntity.getTitle());
    viewCreatedDate.setText(viewEntity.getPurchaseDate());
    viewCheckedItemsCount.setText(viewEntity.getShoppingList().getCheckedItemsCount());
    viewOptions.setOnClickListener(v -> {
      openPopupMenu(viewEntity.getShoppingList(), viewEntity.getListeners());
    });
    itemView.setOnClickListener(v -> {
      viewEntity.getListeners().onArchiveShoppingListClick(viewEntity.getShoppingList().getId(),
        viewEntity.getShoppingList().getName());
    });
  }

  public void openPopupMenu(ShoppingList shoppingList, ArchiveShoppingListClickListeners listeners) {
    PopupMenu popupMenu = new PopupMenu(itemView.getContext(), viewOptions);
    popupMenu.getMenuInflater().inflate(R.menu.msla_archived_shopping_list_options_menu, popupMenu.getMenu());
    popupMenu.setOnMenuItemClickListener(item -> {
      if (item.getItemId() == R.id.item_unarchive) {
        listeners.onUnarchiveShoppingListClick(shoppingList);
      } else if (item.getItemId() == R.id.item_remove) {
        listeners.onRemoveArchiveShoppingListClick(shoppingList);
      }
      return true;
    });
    popupMenu.show();
  }

  public interface ArchiveShoppingListClickListeners {
    void onRemoveArchiveShoppingListClick(ShoppingList shoppingList);

    void onUnarchiveShoppingListClick(ShoppingList shoppingList);

    void onArchiveShoppingListClick(ShoppingListId shoppingListId, String shoppingListTitle);
  }
}
