package com.example.templatesampleapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.templatesampleapp.R
import com.example.templatesampleapp.adapter.holders.AccountItemViewHolder
import com.example.templatesampleapp.adapter.holders.PurposeItemViewHolder
import com.example.templatesampleapp.base.BaseListAdapter
import com.example.templatesampleapp.databinding.AccountListItemBinding
import com.example.templatesampleapp.databinding.PurposeListItemBinding
import com.example.templatesampleapp.model.uimodel.AccountsListItem
import com.example.templatesampleapp.model.uimodel.PurposeListItem

/**
 * This is only for Sample to Show
 * How to impl Generic Adapter in whole application
 * you may delete it when Impl this template in your project
 */
class PurposeItemsAdapter(private var singleItemClickListner:(PurposeListItem, Int)->Unit) : BaseListAdapter<PurposeListItem, PurposeListItemBinding, PurposeItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup,inflater: LayoutInflater, viewType: Int): PurposeItemViewHolder {
        val binding=
            DataBindingUtil.inflate<PurposeListItemBinding>(inflater, R.layout.purpose_list_item,parent,false)

        return PurposeItemViewHolder(binding).apply {
            binding.root.setOnClickListener {
                singleItemClickListner.invoke(getItem(adapterPosition),this.adapterPosition)
            }
        }
    }

}