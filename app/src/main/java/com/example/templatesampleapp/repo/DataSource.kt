package com.example.templatesampleapp.repo

import com.example.templatesampleapp.R
import com.example.templatesampleapp.helper.AppBaseSetting
import com.example.templatesampleapp.model.Accounts
import com.example.templatesampleapp.model.Payees
import com.example.templatesampleapp.model.uimodel.PurposeListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DataSource {
    fun getAllListOfPayee(): Flow<List<Payees>> = flow {
        kotlinx.coroutines.delay(AppBaseSetting.waitForDataLoad)
        emit(arrayListOf(
            Payees((R.drawable.man).toString(),"Umer Bila ","000100462824600","Allied Bank"),
            Payees((R.drawable.man1).toString(),"Hairs ","000100462824600","Allied Bank"),
            Payees((R.drawable.man2).toString(),"Ahmad ","000100462824600","Banka Bank"),
            Payees((R.drawable.man).toString(),"Umair ","000100462824600","UBL Bank"),
            Payees((R.drawable.man1).toString(),"Asma ","000100462824600","Alfla Bank"),
            Payees((R.drawable.man2).toString(),"Khaild Abbasi ","000100462824600","Allied Bank")
        ))
    }

    fun getAllAccountList(): Flow<List<Accounts>> = flow {
        //kotlinx.coroutines.delay(AppBaseSetting.waitForDataLoad)
        emit(arrayListOf(
            Accounts("Umer Bila ","000100462824600","112,231,011"),
            Accounts("Hairs ","000100462824600","1000"),
            Accounts("Ahmad ","000100462824600","112,231,011"),
            Accounts("Umair ","000100462824600","1212,3432,23423"),
            Accounts("Asma ","000100462824600","00000120430"),
            Accounts("Khaild Abbasi ","000100462824600","1212,3432,234")
        ))
    }

    fun getAllTransPurposeList(): Flow<List<PurposeListItem>> = flow {
        ///kotlinx.coroutines.delay(AppBaseSetting.waitForDataLoad)
        emit(arrayListOf(
            PurposeListItem("Family"),
            PurposeListItem("Business"),
            PurposeListItem("Education"),
            PurposeListItem("Donation"),
            PurposeListItem("Others")
        ))
    }


}