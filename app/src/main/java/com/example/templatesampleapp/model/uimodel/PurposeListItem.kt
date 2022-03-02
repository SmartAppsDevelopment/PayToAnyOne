package com.example.templatesampleapp.model.uimodel

import android.os.Parcelable
import com.example.templatesampleapp.base.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class PurposeListItem(val name: String) :
    BaseModel(), Parcelable