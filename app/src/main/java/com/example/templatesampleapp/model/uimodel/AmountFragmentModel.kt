package com.example.templatesampleapp.model.uimodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class AmountFragmentModel:BaseObservable(){
    var amount:String="0"

    @JvmName("getAmount1")
    @Bindable
    fun getAmount(): String {
       // showLog("GET ")
        return amount
    }

    @JvmName("setAmount1")
    fun setAmount(value: String) {
       // showLog("SET "+value)
        amount = value.ifEmpty { "0" }
    }
}