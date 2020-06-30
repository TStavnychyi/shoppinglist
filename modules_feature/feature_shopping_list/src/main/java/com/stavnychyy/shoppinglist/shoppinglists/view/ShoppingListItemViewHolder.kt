package com.stavnychyy.shoppinglist.shoppinglists.view

import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.stavnychyy.shoppinglist.domain.ShoppingList
import com.stavnychyy.shoppinglist.shoppinglists.R
import com.stavnychyy.shoppinglist.shoppinglists.viewmodel.ShoppingListItemViewEntity
import kotlinx.android.synthetic.main.msla_shopping_list_adapter_item.view.*

class ShoppingListItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

  fun applyViewEntity(viewEntity: ShoppingListItemViewEntity) {
    with(viewEntity) {
      itemView.view_title.text = title
      itemView.view_created_date.text = createdDate
      itemView.view_checked_items_count.text = shoppingList.getCheckedItemsCount()
      itemView.view_options.setOnClickListener {
        openPopupMenu(
          viewEntity.shoppingList,
          onRemoveItemClickListener = viewEntity.onDeleteItemClickListener,
          onArchiveItemClickListener = viewEntity.onArchiveItemClickListener
        )
      }
      itemView.setOnClickListener { viewEntity.onItemClickListener() }
    }
  }

  private fun openPopupMenu(
    shoppingList: ShoppingList,
    onRemoveItemClickListener: (id: ShoppingList) -> Unit,
    onArchiveItemClickListener: (id: ShoppingList) -> Unit
  ) {
    with(PopupMenu(itemView.context, itemView.view_options)) {
      menuInflater.inflate(R.menu.msla_shopping_list_item_popup_menu, menu)
      setOnMenuItemClickListener {
        if (it.itemId == R.id.item_archive) {
          onArchiveItemClickListener(shoppingList)
        }
        if (it.itemId == R.id.item_remove) {
          onRemoveItemClickListener(shoppingList)
        }
        true
      }
      show()
    }
  }
}
