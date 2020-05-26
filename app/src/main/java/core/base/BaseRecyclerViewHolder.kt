package base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView

/*
*Project Name : Fujairah                   
*Created by   : Azmin Purushotham              
*Date         : 24/11/19 11:45 AM      
*/
open class BaseRecyclerViewHolder constructor(val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root), LifecycleOwner {
    private val lifecycleRegistry = LifecycleRegistry(this)

    init {
        lifecycleRegistry.currentState = Lifecycle.State.INITIALIZED
    }

    fun markAttach() {
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
    }

    fun markDetach() {
        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }
}