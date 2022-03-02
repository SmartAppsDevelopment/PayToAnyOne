package com.example.templatesampleapp.repo

import com.example.templatesampleapp.base.PayeeRepository
import com.example.templatesampleapp.model.Accounts
import com.example.templatesampleapp.model.Payees
import com.example.templatesampleapp.model.uimodel.PurposeListItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PayeesRepoImpl @Inject constructor(private val dataSource: DataSource):PayeeRepository {
    override fun getAllPayeesList(): Flow<List<Payees>> {
        return dataSource.getAllListOfPayee()
    }

    override fun getAllAccountList(): Flow<List<Accounts>> {
        return dataSource.getAllAccountList()
    }

    override fun getAllTransPurposeList(): Flow<List<PurposeListItem>> {
        return dataSource.getAllTransPurposeList()
    }
}