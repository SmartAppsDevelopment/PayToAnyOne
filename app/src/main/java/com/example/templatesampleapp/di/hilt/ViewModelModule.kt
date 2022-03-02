package com.example.templatesampleapp.di.hilt

import com.example.templatesampleapp.base.PayeeRepository
import com.example.templatesampleapp.repo.DataSource
import com.example.templatesampleapp.repo.PayeesRepoImpl
import com.example.templatesampleapp.ui.activmain.MainViewModel
import com.example.templatesampleapp.ui.frag_payes.PayeesViewModel
import com.example.templatesampleapp.ui.payeedetails.PayeesDetailViewModel
import com.example.templatesampleapp.ui.paynow.PayNowViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun getDataSource()=DataSource()

    @Provides
    fun getRepoImpl(dataSource: DataSource):PayeeRepository= PayeesRepoImpl(dataSource)

    @Provides
    fun getPayeesViewModel(payeeRepository: PayeeRepository)=PayeesViewModel(payeeRepository)

    @Provides
    fun getMainViewModel()=MainViewModel()

    @Provides
    fun getPayeesDetailViewModel()=PayeesDetailViewModel()

}