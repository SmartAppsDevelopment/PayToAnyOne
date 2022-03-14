package com.example.templatesampleapp.model.uimodel

data class DialogModel(val senderName:String,val receiverName:String,val amount:String,val callBackShare:()->Unit){
    var accountNo:String=""
}