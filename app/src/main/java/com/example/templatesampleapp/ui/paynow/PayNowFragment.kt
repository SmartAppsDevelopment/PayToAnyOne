package com.example.templatesampleapp.ui.paynow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.example.templatesampleapp.R
import com.example.templatesampleapp.adapter.AccountItemsAdapter
import com.example.templatesampleapp.adapter.PurposeItemsAdapter
import com.example.templatesampleapp.base.BaseFragment
import com.example.templatesampleapp.databinding.FragmentPayNowBinding
import com.example.templatesampleapp.helper.*
import com.example.templatesampleapp.model.Payees
import com.example.templatesampleapp.model.uimodel.AccountsListItem
import com.example.templatesampleapp.model.uimodel.PurposeListItem
import com.example.templatesampleapp.model.uimodel.ToolBarRef
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PayNowFragment :
    BaseFragment<FragmentPayNowBinding>(R.layout.fragment_pay_now) {

    private val payeesDetails by navArgs<PayNowFragmentArgs>()
    override val viewModel by activityViewModels<PayNowViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCurrentUserOnCard()
        setCardViewData()
        observeData()

    }

    private fun observeData() {
        binding.expandPurposeView.root.setOnClickListener {
            val bol = binding.isPurposeViewExpanded ?: false
            binding.isPurposeViewExpanded = !bol
            binding.accountExpansion.visibility=View.GONE
        }
        binding.expandAccView.root.setOnClickListener {
            val bol = binding.isAccViewExpanded ?: false
            binding.isAccViewExpanded = !bol
        }
        viewModel.viewModelScope.launch {
            observeAccountChange()
        }
        viewModel.isContinueEnabled.observe(viewLifecycleOwner) {
            showLog("yes its lock isEnabled=$it")
            binding.btnContinue.isEnabled = it
        }
    }

    private fun setCurrentUserOnCard() {
        payeesDetails.payesDetails?.let {
            binding.datamodel = it.toCardViewRef({
                showLog("Edit Click ")
            }, {
                showLog("Del Click ")
            }).apply {
                hideEdit = true
                hideDelete = true
            }
        }
    }

    private fun setCardViewData() {
        binding.model = payeesDetails.payesDetails
    }

    private suspend fun observeAccountChange() {
        viewModel.currentSelectAccount.observe(viewLifecycleOwner) {
            binding.isAccViewExpanded = false
            binding.expandAccView.model = it
        }
        viewModel.getDataFromLocalDb()
        viewModel.getPurposeDataFromLocalDb()
        viewModel.uiUpdates.collect { it ->
            when (it) {
                is ResponseState.Error -> {
                }
                is ResponseState.Idle -> {
                }
                is ResponseState.Loading -> {
                }
                is ResponseState.Success -> {
                    val mappedList = it.data!!.map {
                        it!!.toAccountListItem()
                    }
                    updateDataInExpandView(mappedList)

                }
            }
        }
        viewModel.uiUpdatesPurposeItem.collect { listItem ->
            when (listItem) {
                is ResponseState.Error -> {
                }
                is ResponseState.Idle -> {
                }
                is ResponseState.Loading -> {
                }
                is ResponseState.Success -> {
                    val mappedList = listItem.data!!.map {
                        it!!
                    }
                    updateDataInExpandViewPurpose(mappedList)

                }
            }
        }
    }

    private fun updateDataInExpandView(data: List<AccountsListItem>) {
        binding.expandAccView.rvItemslist.adapter = AccountItemsAdapter { item, index ->
            showLog(item.toString())
            viewModel.currentSelectAccount.value=(item.toAccountItem())
        }.apply {
            submitList(data)
        }

    }
    private fun updateDataInExpandViewPurpose(data: List<PurposeListItem>?) {
        binding.expandPurposeView.rvItemslist.adapter = PurposeItemsAdapter { item, index ->
            showLog(item.toString())
            viewModel.currentTransPurpose.value=(item)
        }.apply {
            submitList(data)
        }

    }

    override fun getToolbar() = ToolBarRef("Pay", searchClick = {
        showLog("1 Search Click")
    }, userImgClick = {
        showLog("1 Img CLick")
    })


}