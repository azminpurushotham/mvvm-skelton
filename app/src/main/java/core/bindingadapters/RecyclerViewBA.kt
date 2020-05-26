package core.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import base.BaseRecyclerViewListAdapter

/*
*Project Name : Aqdar                   
*Created by   : Azmin Purushotham              
*Date         : 28/3/20 11:28 AM      
*/
object RecyclerViewBA {
    @JvmStatic
    @BindingAdapter(value = ["app:adapter", "app:list"], requireAll = true)
    fun <T> RecyclerView.setAdapter(adapter: BaseRecyclerViewListAdapter<T>?, list: List<T>?) {
        if (list != null && list.isNotEmpty() && adapter != null) {
            if (this.adapter==null) {
                this.adapter = adapter
                adapter?.submitList(list)
            }
        }
    }
}