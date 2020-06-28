package com.stavnychyy.shoppinglist.view.adapter

import android.util.TypedValue
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.stavnychyy.shoppinglist.extensions.visible
import com.stavnychyy.shoppinglist.view.ShoppingListDetailsFragmentDirections
import kotlinx.android.synthetic.main.msla_edit_shopping_list_adapter_item_layout.view.*

class ShoppingListItemsViewHolder(
  private val isInReadMode: Boolean,
  view: View
) : RecyclerView.ViewHolder(view) {

  fun applyViewEntity(viewEntity: ShoppingListItemViewEntity) {
    with(viewEntity) {
      changeParentBackground(isInReadMode)
      itemView.view_title.text = title
      itemView.view_item_radio.isChecked = isChecked
      itemView.view_item_radio.isEnabled = !isInReadMode
      if (notes.isNotEmpty()) {
        itemView.view_item_subtitle.text = notes
        itemView.view_item_subtitle.visible()
      }
      if (!isInReadMode) {
        itemView.setOnClickListener {
          val action =
            ShoppingListDetailsFragmentDirections.actionShoppingListDetailsFragmentToAddShoppingListItemBottomSheet(
              title,
              notes
            )
          itemView.findNavController().navigate(action)
        }
      }
    }
  }

  private fun changeParentBackground(isInReadMode: Boolean) {
    with(itemView) {
      background = if (isInReadMode) {
        null
      } else {
        val outValue = TypedValue()
        context.theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
        context.getDrawable(outValue.resourceId)
      }
    }
  }
}
