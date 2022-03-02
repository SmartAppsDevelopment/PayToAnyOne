package com.example.templatesampleapp.ui.frag_payes

import com.example.templatesampleapp.base.BaseViewModel
import com.example.templatesampleapp.base.PayeeRepository
import com.example.templatesampleapp.helper.ResponseState
import com.example.templatesampleapp.model.Payees
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class PayeesViewModel @Inject constructor(private val repository: PayeeRepository):BaseViewModel() {


    val uiUpdates =
        MutableStateFlow<ResponseState<List<Payees?>>>(ResponseState.Idle("Ideal State"))


    suspend fun getDataFromLocalDb() {
        uiUpdates.emit(ResponseState.Loading())
        repository.getAllPayeesList().collect {
            uiUpdates.emit(ResponseState.Success(it))
        }
    }

}