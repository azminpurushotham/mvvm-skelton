package core.bindingadapters

import android.widget.Spinner
import androidx.databinding.BindingAdapter
import core.bindingadapters.SpinnerExtensions.setSpinnerEntries
import core.bindingadapters.SpinnerExtensions.setSpinnerInitial
import core.bindingadapters.SpinnerExtensions.setSpinnerItemSelectedListener
import core.bindingadapters.SpinnerExtensions.setSpinnerValue

object SpinnerBindings {

    @JvmStatic
    @BindingAdapter(value = ["entries", "dropdown_layout", "selection_layout"], requireAll = false)
    fun Spinner.setEntries(entries: List<Any>?, dropdown_layout: Int?, selection_layout: Int?) {
        setSpinnerEntries(entries, dropdown_layout, selection_layout)
    }

    @JvmStatic
    @BindingAdapter("onItemSelected")
    fun Spinner.setItemSelectedListener(itemSelectedListener: SpinnerExtensions.ItemSelectedListener?) {
        setSpinnerItemSelectedListener(itemSelectedListener)
    }

    @JvmStatic
    @BindingAdapter("newValue")
    fun Spinner.setNewValue(newValue: Any?) {
        setSpinnerValue(newValue)
    }

    @JvmStatic
    @BindingAdapter("initalSelection")
    fun Spinner.setInitalSelection(newValue: Any?) {
        setSpinnerInitial(newValue)
    }

    @JvmStatic
    @BindingAdapter("selectedValue")
    fun Spinner.setSelectedValue(selectedValue: Any?) {
        setSpinnerValue(selectedValue)
    }
}