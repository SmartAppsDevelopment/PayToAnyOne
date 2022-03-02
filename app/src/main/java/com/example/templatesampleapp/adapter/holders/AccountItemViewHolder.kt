package com.example.templatesampleapp.adapter.holders

import com.example.templatesampleapp.base.BaseViewHolder
import com.example.templatesampleapp.databinding.AccountListItemBinding
import com.example.templatesampleapp.model.Accounts
import com.example.templatesampleapp.model.uimodel.AccountsListItem

open class AccountItemViewHolder(binding: AccountListItemBinding):BaseViewHolder<AccountsListItem,AccountListItemBinding>(binding){
    override fun bind(model: AccountsListItem) {
        binding.model= model
        binding.executePendingBindings()
    }
}