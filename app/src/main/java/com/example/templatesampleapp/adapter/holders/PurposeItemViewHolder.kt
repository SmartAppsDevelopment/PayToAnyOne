package com.example.templatesampleapp.adapter.holders

import com.example.templatesampleapp.base.BaseViewHolder
import com.example.templatesampleapp.databinding.AccountListItemBinding
import com.example.templatesampleapp.databinding.PurposeListItemBinding
import com.example.templatesampleapp.model.Accounts
import com.example.templatesampleapp.model.uimodel.AccountsListItem
import com.example.templatesampleapp.model.uimodel.PurposeListItem

open class PurposeItemViewHolder(binding: PurposeListItemBinding):BaseViewHolder<PurposeListItem,PurposeListItemBinding>(binding){
    override fun bind(model: PurposeListItem) {
        binding.model= model
        binding.executePendingBindings()
    }
}