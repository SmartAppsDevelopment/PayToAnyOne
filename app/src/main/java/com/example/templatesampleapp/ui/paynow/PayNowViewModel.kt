package com.example.templatesampleapp.ui.paynow

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
class PayNowViewModel @Inject constructor(
    private var repository: PayeeRepository,
    private var savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    companion object {
        private const val USER_ACCOUNT = "USER_ACCOUNT"
        private const val USER_ACCOUNT_PURPOSE = "USER_ACCOUNT_PURPOSE"
    }

    /*
    TODO: check is both account and purpose of payment has been slected
     */
    var isAccountSelected=false
    set(value) {
        field=value
        verifyTransaction()
    }
    var isPurposeSelected=false
    set(value) {
        field=value
        verifyTransaction()
    }
    var isContinueEnabled = MutableLiveData(false)

    var currentTransPurpose = MutableLiveData<PurposeListItem>()
        set(value) {
           /// savedStateHandle.set(USER_ACCOUNT_PURPOSE, value)
            field = value
        }
        get() = savedStateHandle.getLiveData(USER_ACCOUNT_PURPOSE)

    var currentSelectAccount = MutableLiveData<Accounts>()
        set(value) {
            savedStateHandle.set(USER_ACCOUNT, value)
            field = value
        }
        get() = savedStateHandle.getLiveData(USER_ACCOUNT)

    val uiUpdates =
        MutableStateFlow<ResponseState<List<Accounts?>>>(ResponseState.Idle("Ideal State"))

    val uiUpdatesPurposeItem =
        MutableStateFlow<ResponseState<List<PurposeListItem?>>>(ResponseState.Idle("Ideal State"))

    suspend fun getDataFromLocalDb() {
        uiUpdates.emit(ResponseState.Loading())
        repository.getAllAccountList().collect {
            currentSelectAccount.value=(it.get(0)!!)
            uiUpdates.emit(ResponseState.Success(it))
        }
    }
    suspend fun getPurposeDataFromLocalDb() {
        uiUpdatesPurposeItem.emit(ResponseState.Loading())
        repository.getAllTransPurposeList().collect {accountList->
            currentTransPurpose.value= PurposeListItem(Constants.PURPOSE_HEADING)
           isPurposeSelected = false
            uiUpdatesPurposeItem.emit(ResponseState.Success(accountList))
        }
    }

    private fun verifyTransaction(){
        isContinueEnabled.value=(isAccountSelected and isPurposeSelected)
    }
}