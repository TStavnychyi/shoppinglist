package com.stavnychyy.shoppinglist.view

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.stavnychyy.shoppinglist.R
import com.stavnychyy.shoppinglist.di.AddShoppingComponentProvider
import com.stavnychyy.shoppinglist.extensions.getString
import com.stavnychyy.shoppinglist.extensions.inflateNoAttach
import com.stavnychyy.shoppinglist.fragment.BaseBottomSheetDialogFragment
import com.stavnychyy.shoppinglist.viewmodel.AddShoppingListViewModel
import kotlinx.android.synthetic.main.msla_add_shopping_list_fragment.*
import org.threeten.bp.LocalDate
import javax.inject.Inject


class AddShoppingListFragment : BaseBottomSheetDialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<AddShoppingListViewModel> { viewModelFactory }

    private val datePickerDialog by lazy { createDatePickerDialog() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as AddShoppingComponentProvider)
            .provideAddShoppingComponent()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflateNoAttach(R.layout.msla_add_shopping_list_fragment, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
            onFormDataInvalidLiveEvent.observe(viewLifecycleOwner) {
                invalidFormDataSubmitted()
            }
            onShoppingListAddedSuccessfully.observe(viewLifecycleOwner) {
                findNavController().navigateUp()
            }

            view_create_list_button.setOnClickListener {
                saveShoppingList(
                    view_list_name_input.getString(),
                    formatSelectedDate(view_purchase_date_input.getString())
                )
            }
        }

        view_purchase_date_input.setOnClickListener { datePickerDialog.show() }

        view_list_name_input.doAfterTextChanged {
            if (it != null && it.isNotEmpty() && view_purchase_date_input.text.isNotEmpty()) {
                view_create_list_button.isEnabled = true
            }
        }
    }

    private fun createDatePickerDialog(): DatePickerDialog {
        val onDateChangeListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            view_purchase_date_input.text = resources.getString(
                R.string.msla_add_shopping_list_date_placeholder,
                viewModel.formatSelectedDayOrMonth(dayOfMonth),
                viewModel.formatSelectedDayOrMonth(month + 1),
                year
            )
            if (view_list_name_input.text.isNotEmpty()) {
                view_create_list_button.isEnabled = true
            }
        }
        return with(LocalDate.now()) {
            DatePickerDialog(requireContext(), onDateChangeListener, year, monthValue - 1, dayOfMonth).apply {
                datePicker.minDate = System.currentTimeMillis() - 1000
            }
        }
    }

    private fun invalidFormDataSubmitted() {
        view_create_list_button.isEnabled = false
        Snackbar.make(
            requireView(),
            R.string.msla_add_shopping_list_incorrectly_entered_data_message,
            Snackbar.LENGTH_LONG
        ).show()
    }
}
