package com.azmin.skelton.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import base.BaseRecyclerViewAdapter
import com.azmin.skelton.R
import com.azmin.skelton.databinding.ItemHomeListBinding
import timber.log.Timber

class HomeAdapter(
    var _list: List<String>,
    private var listener: OnItemClickListener
) : BaseRecyclerViewAdapter<String>(
    diffCallback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            if (oldItem == newItem) {
                Timber.v("****** areItemsTheSame  same")
            } else {
                Timber.v("****** areItemsTheSame   different")
            }
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            if (oldItem.equals(newItem)) {
                Timber.v("****** areContentsTheSame same")
            } else {
                Timber.v("****** areContentsTheSame  different")
            }
            return oldItem.equals(newItem)
        }
    }) {

    var list = _list
    private var temp: List<String> = emptyList()

    interface OnItemClickListener {
        fun onItemClickListener(position: Int)
        fun onDetailClick(position: Int, item: String)
    }

    override fun getItemCount(): Int {
        if (list != null) {
            Timber.v("Size ManageMemberListAdapter " + list.size)
            return list.size
        } else {
            Timber.v("Size ManageMemberListAdapter " + 0)
            return 0
        }
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val binding = DataBindingUtil.inflate<ItemHomeListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_home_list,
            parent,
            false
        )
        return binding
    }

    override fun bind(binding: ViewDataBinding, item: String, position: Int) {
        when (binding) {
            is ItemHomeListBinding -> {
                var bind: ItemHomeListBinding = binding
                bind.vm = item
                bind.root.setOnClickListener {
                    listener.onDetailClick(position, list[position])
                }
            }
        }
    }

}



