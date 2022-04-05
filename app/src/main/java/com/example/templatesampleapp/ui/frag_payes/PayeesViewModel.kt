package com.example.templatesampleapp.ui.frag_payes

import androidx.lifecycle.MutableLiveData
import com.example.templatesampleapp.base.BaseViewModel
import com.example.templatesampleapp.base.PayeeRepository
import com.example.templatesampleapp.helper.ResponseState
import com.example.templatesampleapp.model.Payees
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class PayeesViewModel @Inject constructor(private val repository: PayeeRepository):BaseViewModel() {


    val uiUpdates =
        MutableStateFlow<ResponseState<List<Payees?>>>(ResponseState.Idle("Ideal State"))

val showProgressBar= MutableStateFlow(false)
val showRvView=MutableStateFlow(false)
    suspend fun getDataFromLocalDb() {
        uiUpdates.emit(ResponseState.Loading())
        repository.getAllPayeesList().collect {
            delay(2000)
            uiUpdates.emit(ResponseState.Success(it))
        }
    }

    fun showLoading() {
       showProgressBar.value=true
        showRvView.value=false

    }

    fun hideLoading() {
        showProgressBar.value=false
        showRvView.value=true
    }

}