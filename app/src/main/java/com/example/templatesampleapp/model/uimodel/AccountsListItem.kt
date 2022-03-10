package com.example.templatesampleapp.model.uimodel

import android.os.Parcelable
import com.example.templatesampleapp.base.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class AccountsListItem(val name: String, val accountNumber: String, val amount: String) :
    BaseModel(), Parcelable