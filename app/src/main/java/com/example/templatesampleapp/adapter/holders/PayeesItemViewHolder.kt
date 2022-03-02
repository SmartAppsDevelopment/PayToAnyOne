package com.example.templatesampleapp.adapter.holders

import com.example.templatesampleapp.base.BaseViewHolder
import com.example.templatesampleapp.databinding.PayeesListItemBinding
import com.example.templatesampleapp.model.Payees

open class PayeesItemViewHolder(binding: PayeesListItemBinding):BaseViewHolder<Payees,PayeesListItemBinding>(binding){
    override fun bind(model: Payees) {
        binding.model= model
        binding.executePendingBindings()
    }
}