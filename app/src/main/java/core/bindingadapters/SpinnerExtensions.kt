package core.bindingadapters

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.InverseBindingListener
import core.base.BaseSpinnerAdapter
import timber.log.Timber


object SpinnerExtensions {

    /**
     * set spinner entries
     */
    fun Spinner.setSpinnerEntries(
        entries: List<Any>?,
        dropdown_layout: Int?,
        selection_layout: Int?
    ) {
        if (entries != null) {
            adapter = BaseSpinnerAdapter(
                context,
                entries,
                dropdown_layout,
                selection_layout
            )
//            isSelected = false
        }
    }

    /**
     * set spinner onItemSelectedListener listener
     */
    fun Spinner.setSpinnerItemSelectedListener(listener: ItemSelectedListener?) {

        if (listener == null) {
            onItemSelectedListener = null
        } else {
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    if (tag != position) {
                        listener.onItemSelected(parent.getItemAtPosition(position))
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }
        }
    }

    /**
     * set spinner onItemSelectedListener listener
     */
    fun Spinner.setSpinnerInverseBindingListener(listener: InverseBindingListener?) {
        if (listener == null) {
            onItemSelectedListener = null
        } else {
//            isSelected = false
//            setSelection(Adapter.NO_SELECTION, false)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    if (tag != position) {
                        listener.onChange()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    Timber.v("Nothing selected")
                }
            }
        }
    }

    /**
     * set spinner value
     */
    fun Spinner.setSpinnerValue(value: Any?) {
        if (adapter != null && value != null) {
            val position = (adapter as ArrayAdapter<Any>).getPosition(value)
            setSelection(position, false)
            tag = position
        }
    }

    /**
     * set spinner value
     */
    fun Spinner.setSpinnerInitial(value: Any?) {
        if (adapter != null && value != null) {
            val position = (adapter as ArrayAdapter<Any>).getPosition(value)
            setSelection(position, false)
            isSelected = true
            tag = position
        }else if(adapter != null && value == null){
           setSelection(0, false)
            isSelected = false
        }
    }

    /**
     * get spinner value
     */
    fun Spinner.getSpinnerValue(): Any? {
        return selectedItem
    }

    interface ItemSelectedListener {
        fun onItemSelected(item: Any?)
    }

}