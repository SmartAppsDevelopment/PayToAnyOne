package com.example.templatesampleapp.model.uimodel

data class CardViewRef(var name:String,val accNo:String,val bankName:String,val imgName:String,val editClick:()->Unit,val delClick:()->Unit){
    var hideEdit=false
    var hideDelete=false
}