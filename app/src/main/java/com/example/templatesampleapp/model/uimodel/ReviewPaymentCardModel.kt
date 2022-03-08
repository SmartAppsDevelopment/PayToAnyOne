package com.example.templatesampleapp.model.uimodel

data class ReviewPaymentCardModel(
    val payFromName: String,
    val payToName: String,
    val senderAcNo: String,
    val receiverAcNo:String,
    val receiverBankName:String,
    val date:String
)