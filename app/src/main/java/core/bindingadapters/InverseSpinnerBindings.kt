package core.bindingadapters

import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import core.bindingadapters.SpinnerExtensions.getSpinnerValue
import core.bindingadapters.SpinnerExtensions.setSpinnerInitial
import core.bindingadapters.SpinnerExtensions.setSpinnerInverseBindingListener
import core.bindingadapters.SpinnerExtensions.setSpinnerValue

object InverseSpinnerBindings {

    @JvmStatic
    @BindingAdapter("selectedValue")
    fun Spinner.setSelectedValue(selectedValue: Any?) {
        setSpinnerValue(selectedValue)
    }

    @JvmStatic
    @BindingAdapter("selectedValueAttrChanged")
    fun Spinner.setInverseBindingListener(inverseBindingListener: InverseBindingListener?) {
        setSpinnerInverseBindingListener(inverseBindingListener)
    }

    @JvmStatic
    @InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
    fun Spinner.getSelectedValue(): Any? {
        return getSpinnerValue()
    }

    @JvmStatic
    @BindingAdapter("stayInit")
    fun Spinner.setStayInit(selectedValue: Any?) {
        return setSpinnerInitial(selectedValue)
    }

}