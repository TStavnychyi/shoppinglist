package com.stavnychyy.shoppinglist.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.stavnychyy.shoppinglist.viewmodel.ShoppingListItemViewEntity
import kotlinx.android.synthetic.main.msla_shopping_list_adapter_item.view.*


class ShoppingListItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun applyViewEntity(viewEntity: ShoppingListItemViewEntity) {
        with(viewEntity) {
            itemView.view_title.text = title
            itemView.view_created_date.text = createdDate
            itemView.view_status.text = status
            itemView.setOnClickListener { viewEntity.onItemClickListener() }
        }
    }
}
