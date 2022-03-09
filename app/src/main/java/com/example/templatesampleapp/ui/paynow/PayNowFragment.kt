package com.example.templatesampleapp.ui.paynow

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.templatesampleapp.R
import com.example.templatesampleapp.adapter.AccountItemsAdapter
import com.example.templatesampleapp.adapter.PurposeItemsAdapter
import com.example.templatesampleapp.base.BaseFragment
import com.example.templatesampleapp.databinding.FragmentPayNowBinding
import com.example.templatesampleapp.helper.*
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
        binding.btnContinue.setOnClickListener {
            val dir =
                PayNowFragmentDirections.actionPayNowFragmentToAmountFragment(payeesDetails.payesDetails!!)
            dir.transAaccount = viewModel.currentSelectAccount.value
            dir.transPurpose = viewModel.currentTransPurpose.value
            findNavController().safeNavigate(dir)
        }
        binding.expandPurposeView.root.setOnClickListener {
            val bol = binding.isPurposeViewExpanded ?: false
            binding.isPurposeViewExpanded = !bol
            binding.expandAccView.root.visibility = if (bol) View.VISIBLE else View.GONE
        }
        binding.expandAccView.root.setOnClickListener {
            val bol = binding.isAccViewExpanded ?: false
            binding.isAccViewExpanded = !bol
            //  binding.expandPurposeView.root.visibility= if(bol) View.VISIBLE else View.GONE

        }
        viewModel.viewModelScope.launch {
            observeAccountChange()
        }
        lifecycleScope.launchWhenResumed {
            observePurposeChanges()
        }
        viewModel.isContinueEnabled.observe(viewLifecycleOwner) {
            showLog("yes its lock isEnabled=$it")
            binding.btnContinue.isEnabled = it
        }
        with(binding.cardView) {
            ivUserIcon.setImageResource(R.drawable.icon_menu_mobile_top_up_resize)
            hideTxtOnImg=true
           ivUserIcon.setBackgroundColor(Color.WHITE)
        }
    }

    private fun setCurrentUserOnCard() {
        payeesDetails.payesDetails?.let {
            viewModel.isAccountSelected = true
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
        viewModel.currentTransPurpose.observe(viewLifecycleOwner) {
            binding.expandPurposeView.model = it

            if (binding.isPurposeViewExpanded == true)
                binding.expandPurposeView.root.callOnClick()

        }


        viewModel.getDataFromLocalDb()
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

    }

    private suspend fun observePurposeChanges() {
        viewModel.getPurposeDataFromLocalDb()
        binding.viewmodel = viewModel
        viewModel.uiUpdatesPurposeItem.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .collect { listItem ->
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
            viewModel.currentSelectAccount.value = item.toAccountItem()
            viewModel.isAccountSelected = true
        }.apply {
            submitList(data)
        }
    }

    private fun updateDataInExpandViewPurpose(data: List<PurposeListItem>?) {
        binding.expandPurposeView.rvItemslist.adapter = PurposeItemsAdapter { item, index ->
            showLog(item.toString())
            viewModel.isPurposeSelected = true
            viewModel.currentTransPurpose.value = item
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