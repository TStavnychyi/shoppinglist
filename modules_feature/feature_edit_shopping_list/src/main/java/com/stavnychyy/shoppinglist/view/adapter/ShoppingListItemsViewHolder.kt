package com.stavnychyy.shoppinglist.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.stavnychyy.shoppinglist.extensions.visible
import kotlinx.android.synthetic.main.msla_edit_shopping_list_adapter_item_layout.view.*


class ShoppingListItemsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun applyViewEntity(viewEntity: ShoppingListItemViewEntity) {
        with(viewEntity) {
            itemView.view_title.text = title
            itemView.view_item_radio.isChecked = isChecked
            if (subtitle.isNotEmpty()) {
                itemView.view_item_subtitle.text = subtitle
                itemView.view_item_subtitle.visible()
            }
        }
    }
}
