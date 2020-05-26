package core.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.azmin.skelton.R
import core.bindingadapters.Converter

class BaseSpinnerAdapter(
    context: Context,
    list: List<Any>, val dropdown_layout: Int?, val selection_layout: Int?
) : ArrayAdapter<Any>(context, 0, list) {

    override fun getView(position: Int, recycledView: View?, parent: ViewGroup): View {
        val view = if (selection_layout == null) {

            recycledView ?: LayoutInflater.from(context).inflate(
                R.layout.spinner_item,
                parent,
                false
            )

        } else {
            recycledView ?: LayoutInflater.from(context).inflate(
                selection_layout,
                parent,
                false
            )
        }
        val txtView: TextView = view.findViewById(R.id.txtItem)
        setValues(position, txtView, false)
        return view
    }

    private fun setValues(
        position: Int,
        txtView: TextView,
        isDropDown: Boolean
    ) {
        var temp = ""
        var item = getItem(position)

        if (item is String) {
            txtView.setText(
                Converter.toString(
                    item
                )
            )
        }

        if (position == 0 && isDropDown && txtView.text.contains(context.getString(R.string.select))) {
            txtView.setTextColor(ContextCompat.getColor(txtView.context, R.color.textHintTextColor))
        } else if (position == 0 && !isDropDown && txtView.text.contains(context.getString(R.string.select))) {
//            txtView.setText("")
            txtView.setTextColor(ContextCompat.getColor(txtView.context, R.color.textHintTextColor))
        }

    }

    override fun getDropDownView(position: Int, recycledView: View?, parent: ViewGroup): View {
        return this.createView(position, recycledView, parent, true)
    }

    private fun createView(
        position: Int,
        recycledView: View?,
        parent: ViewGroup,
        isDropDown: Boolean
    ): View {
        val view = if (dropdown_layout == null) {
            recycledView ?: LayoutInflater.from(context).inflate(
                R.layout.spinner_dropdown_item,
                parent,
                false
            )
        } else {
            recycledView ?: LayoutInflater.from(context).inflate(
                dropdown_layout!!,
                parent,
                false
            )
        }
        val txtView: TextView = view.findViewById(R.id.txtDpItem)
        setValues(position, txtView, isDropDown)
        return view
    }
}