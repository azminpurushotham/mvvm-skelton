package base

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

/*
*Project Name : Recta
*Created by   : Azmin Purushotham              
*Date         : 24/11/19 11:36 AM      
*/
abstract class BaseRecyclerViewAdapter<T>(
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, BaseRecyclerViewHolder>(
    AsyncDifferConfig.Builder<T>(diffCallback)
        .build()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder {
        val binding = createBinding(parent, viewType)
        val viewHolder = BaseRecyclerViewHolder(binding)
        return viewHolder
    }

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder, position: Int) {
        if (position < itemCount) {
            try {
                bind(holder.binding, getItem(position), position)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            try {
                holder.binding.executePendingBindings()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    protected abstract fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding
    protected abstract fun bind(binding: ViewDataBinding, item: T, position: Int)
}