package com.stavnychyy.shoppinglist.shoppinglistdetails.view.adapter

import android.graphics.Paint
import android.util.TypedValue
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.stavnychyy.shoppinglist.common.extensions.visible
import com.stavnychyy.shoppinglist.common.extensions.visibleOrGone
import com.stavnychyy.shoppinglist.parcelabledomain.PCLShoppingListId
import com.stavnychyy.shoppinglist.parcelabledomain.PCLShoppingListItemId
import com.stavnychyy.shoppinglist.shoppinglistdetails.R
import com.stavnychyy.shoppinglist.shoppinglistdetails.view.ShoppingListDetailsFragmentDirections
import kotlinx.android.synthetic.main.msla_shopping_list_details_adapter_item_layout.view.*

class ShoppingListItemsViewHolder(
  private val isInReadMode: Boolean,
  view: View
) : RecyclerView.ViewHolder(view) {

  fun applyViewEntity(viewEntity: ShoppingListItemViewEntity) {
    with(viewEntity) {
      changeParentBackground(isInReadMode)
      setStrikeThroughTextIfItemIsChecked(isChecked)
      itemView.view_title.text = title
      itemView.view_item_delete.visibleOrGone(!isInReadMode)
      itemView.view_item_checkbox.apply {
        isChecked = viewEntity.isChecked
        isEnabled = !isInReadMode
      }
      if (notes.isNotEmpty()) {
        itemView.view_item_subtitle.apply {
          text = notes
          visible()
        }
      }
      bindClickListeners(viewEntity)
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

  private fun setStrikeThroughTextIfItemIsChecked(isChecked: Boolean) {
    itemView.view_title.apply {
      paintFlags = if (isChecked) {
        paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
      } else {
        paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
      }
    }
    itemView.view_item_subtitle.apply {
      paintFlags = if (isChecked) {
        paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
      } else {
        paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
      }
    }
  }

  private fun bindClickListeners(viewEntity: ShoppingListItemViewEntity) {
    if (!isInReadMode) {
      itemView.setOnClickListener {
        val action =
          ShoppingListDetailsFragmentDirections.actionShoppingListDetailsFragmentToAddShoppingListItemBottomSheet(
            viewEntity.title,
            viewEntity.notes,
            viewEntity.shoppingListItem.id?.let { PCLShoppingListItemId(it) }
          )
        itemView.findNavController().navigate(action)
      }

      itemView.view_item_delete.setOnClickListener {
        showDeleteItemConfirmationDialog {
          viewEntity.onItemDeleteClickListener(viewEntity.shoppingListItem)
        }
      }

      itemView.view_item_checkbox.setOnCheckedChangeListener { _, isChecked ->
        setStrikeThroughTextIfItemIsChecked(isChecked)
        viewEntity.onItemCheckedChangeListener(viewEntity.shoppingListItem.copy(isChecked = isChecked))
      }
    }
  }

  private fun showDeleteItemConfirmationDialog(onItemDeleteClickListener: () -> Unit) {
    AlertDialog.Builder(itemView.context, R.style.ConfirmationDialog)
      .setTitle(itemView.resources.getString(R.string.msla_shopping_list_details_delete_item_confirmation_dialog_title))
      .setPositiveButton(R.string.msla_shopping_list_details_delete_item_confirmation_dialog_confirm) { _, _ ->
        onItemDeleteClickListener()
      }
      .setNegativeButton(R.string.msla_shopping_list_details_delete_item_confirmation_dialog_cancel) { dialog, _ ->
        dialog.dismiss()
      }
      .show()
  }
}
