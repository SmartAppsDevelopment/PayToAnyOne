package com.example.templatesampleapp.model

import android.os.Parcelable
import com.example.templatesampleapp.base.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Accounts( val name:String, val accountNumber:String, val amount:String):
    BaseModel(), Parcelable