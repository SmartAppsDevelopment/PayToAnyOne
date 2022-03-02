package com.example.templatesampleapp.model

import android.os.Parcelable
import com.example.templatesampleapp.base.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Payees(val iconDrawableId:String, val name:String, val accountNumber:String, val bankName:String):
    BaseModel(), Parcelable