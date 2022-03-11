package com.example.templatesampleapp.ui.frag_review

import com.example.templatesampleapp.base.BaseViewModel
import com.example.templatesampleapp.helper.getCurrentDateTime
import com.example.templatesampleapp.model.Accounts
import com.example.templatesampleapp.model.Payees
import com.example.templatesampleapp.model.uimodel.FragReviewPayModel
import com.example.templatesampleapp.model.uimodel.ReviewPaymentCardModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReviewPaymentViewModel @Inject constructor() :
    BaseViewModel() {


    fun getPaymentModel(
        payees: Payees,
        accounts: Accounts,
        amount: String,
        callback: () -> Unit
    ) =
        FragReviewPayModel(
            ReviewPaymentCardModel(
                accounts.name,
                payees.name,
                accounts.accountNumber,
                payees.accountNumber,
                payees.bankName,
                getCurrentDateTime()
            ), amount, callback
        )

}