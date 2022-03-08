package com.example.templatesampleapp.model.uimodel

data class FragReviewPayModel(val accCardViewRef:ReviewPaymentCardModel,val amount:String,val callback:()->Unit)