package com.example.templatesampleapp.ui.amount

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.templatesampleapp.base.BaseViewModel
import com.example.templatesampleapp.base.PayeeRepository
import com.example.templatesampleapp.helper.Constants
import com.example.templatesampleapp.helper.ResponseState
import com.example.templatesampleapp.model.Accounts
import com.example.templatesampleapp.model.uimodel.PurposeListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class AmountViewModel @Inject constructor(
) : BaseViewModel() {



    /*
    TODO: check is both account and purpose of payment has been slected
     */
    var isAmountVerified=false
    set(value) {
        field=value
        verifyTransaction()
    }

    var isProceedToPayEnabled = MutableLiveData(false)
    var txtChangeListener:String="0"

    private fun verifyTransaction(){
        isProceedToPayEnabled.value=(isAmountVerified)
    }
}