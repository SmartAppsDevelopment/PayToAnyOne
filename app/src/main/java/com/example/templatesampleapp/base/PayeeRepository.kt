package com.example.templatesampleapp.base

import com.example.templatesampleapp.model.Accounts
import com.example.templatesampleapp.model.Payees
import com.example.templatesampleapp.model.uimodel.PurposeListItem
import kotlinx.coroutines.flow.Flow

interface PayeeRepository {

    fun getAllPayeesList(): Flow<List<Payees>>

    fun getAllAccountList(): Flow<List<Accounts>>

    fun getAllTransPurposeList(): Flow<List<PurposeListItem>>

}