package com.example.templatesampleapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.templatesampleapp.R
import com.example.templatesampleapp.adapter.holders.AccountItemViewHolder
import com.example.templatesampleapp.base.BaseListAdapter
import com.example.templatesampleapp.databinding.AccountListItemBinding
import com.example.templatesampleapp.model.uimodel.AccountsListItem

/**
 * This is only for Sample to Show
 * How to impl Generic Adapter in whole application
 * you may delete it when Impl this template in your project
 */
class AccountItemsAdapter(private var singleItemClickListner:(AccountsListItem, Int)->Unit) : BaseListAdapter<AccountsListItem, AccountListItemBinding, AccountItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup,inflater: LayoutInflater, viewType: Int): AccountItemViewHolder {
        val binding=
            DataBindingUtil.inflate<AccountListItemBinding>(inflater, R.layout.account_list_item,parent,false)

        return AccountItemViewHolder(binding).apply {
            binding.root.setOnClickListener {
                singleItemClickListner.invoke(getItem(adapterPosition),this.adapterPosition)
            }
        }
    }

}