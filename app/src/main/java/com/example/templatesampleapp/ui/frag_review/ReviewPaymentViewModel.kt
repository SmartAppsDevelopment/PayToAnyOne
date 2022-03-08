package com.example.templatesampleapp.ui.frag_review

import androidx.lifecycle.SavedStateHandle
import com.example.templatesampleapp.base.BaseViewModel
import com.example.templatesampleapp.base.PayeeRepository
import com.example.templatesampleapp.helper.ResponseState
import com.example.templatesampleapp.helper.getCurrentDateTime
import com.example.templatesampleapp.model.Accounts
import com.example.templatesampleapp.model.Payees
import com.example.templatesampleapp.model.uimodel.FragReviewPayModel
import com.example.templatesampleapp.model.uimodel.PurposeListItem
import com.example.templatesampleapp.model.uimodel.ReviewPaymentCardModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class ReviewPaymentViewModel @Inject constructor(val savedStateHandle: SavedStateHandle) :
    BaseViewModel() {


    fun getPaymentModel(
        payees: Payees,
        accounts: Accounts,
        purposeListItem: PurposeListItem,
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