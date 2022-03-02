package com.example.templatesampleapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.templatesampleapp.R
import com.example.templatesampleapp.adapter.holders.PayeesItemViewHolder
import com.example.templatesampleapp.base.BaseListAdapter
import com.example.templatesampleapp.databinding.PayeesListItemBinding
import com.example.templatesampleapp.model.Payees

/**
 * This is only for Sample to Show
 * How to impl Generic Adapter in whole application
 * you may delete it when Impl this template in your project
 */
class PayeesitemsAdapter(var singleItemClickListner:(Payees,Int)->Unit) : BaseListAdapter<Payees, PayeesListItemBinding, PayeesItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup,inflater: LayoutInflater, viewType: Int): PayeesItemViewHolder {
        val binding=
            DataBindingUtil.inflate<PayeesListItemBinding>(inflater, R.layout.payees_list_item,parent,false)

        return PayeesItemViewHolder(binding).apply {
            binding.root.setOnClickListener {
                singleItemClickListner.invoke(getItem(adapterPosition),this.adapterPosition)
            }
        }
    }

}