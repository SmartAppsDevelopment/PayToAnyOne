package com.example.templatesampleapp.ui.amount

import androidx.lifecycle.MutableLiveData
import com.example.templatesampleapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
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
    var txtChangeListener:String=""

    private fun verifyTransaction(){
        isProceedToPayEnabled.value=(isAmountVerified)
    }
}