package com.example.templatesampleapp.model.uimodel

import com.example.templatesampleapp.R

data class ToolBarModel(var title: String, var searchClick: () -> Unit, var userImgClick: () -> Unit){
    var hideSearchIcon=true
    var hideSideIcon=true
    set(value) {
        field=true
    }
    var sideButtonText=" "
    var sideButtonIcon:Int= R.drawable.icon_edit
}