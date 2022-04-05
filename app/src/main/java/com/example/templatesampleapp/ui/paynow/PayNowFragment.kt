package com.example.templatesampleapp.ui.paynow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.example.templatesampleapp.R
import com.example.templatesampleapp.base.BaseFragmentCompose
import com.example.templatesampleapp.composecomponent.CraditCardView
import com.example.templatesampleapp.composecomponent.ExpandablePurposeAccountView
import com.example.templatesampleapp.helper.showLog
import com.example.templatesampleapp.model.uimodel.ToolBarModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PayNowFragment : BaseFragmentCompose() {

    private val payeesDetails by navArgs<PayNowFragmentArgs>()
    val viewModel by activityViewModels<PayNowViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            ShowMainUi()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewModelScope.launch {
            viewModel.getPurposeDataFromLocalDb()
            viewModel.getDataFromLocalDb()
        }
//        setCurrentUserOnCard()
//        setCardViewData()
//        observeData()
    }
//
//    private fun observeData() {
//        binding.btnContinue.setOnClickListener {
//            val dir =
//                PayNowFragmentDirections.actionPayNowFragmentToAmountFragment(payeesDetails.payesDetails!!)
//            dir.transAaccount = viewModel.currentSelectAccount.value
//            dir.transPurpose = viewModel.currentTransPurpose.value
//            findNavController().safeNavigate(dir)
//        }
//        binding.expandPurposeView.root.setOnClickListener {
//            val bol = binding.isPurposeViewExpanded ?: false
//            binding.isPurposeViewExpanded = !bol
//            binding.expandAccView.root.visibility = if (bol) View.VISIBLE else View.GONE
//        }
//        binding.expandAccView.root.setOnClickListener {
//            val bol = binding.isAccViewExpanded ?: false
//            binding.isAccViewExpanded = !bol
//            //  binding.expandPurposeView.root.visibility= if(bol) View.VISIBLE else View.GONE
//        }
//        viewModel.viewModelScope.launch {
//            observeAccountChange()
//        }
//        lifecycleScope.launchWhenResumed {
//            observePurposeChanges()
//        }
//        viewModel.isContinueEnabled.observe(viewLifecycleOwner) {
//            showLog("yes its lock isEnabled=$it")
//            binding.btnContinue.isEnabled = it
//        }
////        with(binding.cardView) {
////         //   ivUserIcon.setImageResource(R.drawable.icon_menu_mobile_top_up_resize)
////           // hideTxtOnImg=true
////           //ivUserIcon.setBackgroundColor(Color.WHITE)
////        }
//    }
//
//    private fun setCurrentUserOnCard() {
//        payeesDetails.payesDetails?.let {
//            viewModel.isAccountSelected = true
//            binding.datamodel = it.toCardViewRef({
//                showLog("Edit Click ")
//            }, {
//                showLog("Del Click ")
//            }).apply {
//                hideEdit = true
//                hideDelete = true
//            }
//        }
//    }
//
//    private fun setCardViewData() {
//        binding.model = payeesDetails.payesDetails
//    }
//
//    private suspend fun observeAccountChange() {
//        viewModel.currentSelectAccount.observe(viewLifecycleOwner) {
//            binding.isAccViewExpanded = false
//            binding.expandAccView.model = it
//        }
//        viewModel.currentTransPurpose.observe(viewLifecycleOwner) {
//            binding.expandPurposeView.model = it
//            if (binding.isPurposeViewExpanded == true)
//                binding.expandPurposeView.root.callOnClick()
//        }
//
//        viewModel.getDataFromLocalDb()
//        viewModel.uiUpdates.collect { it ->
//            when (it) {
//                is ResponseState.Error -> {
//                }
//                is ResponseState.Idle -> {
//                }
//                is ResponseState.Loading -> {
//                }
//                is ResponseState.Success -> {
//                    val mappedList = it.data!!.map {
//                        it!!.toAccountListItem()
//                    }
//                    updateDataInExpandView(mappedList)
//
//                }
//            }
//        }
//
//    }
//
//    private suspend fun observePurposeChanges() {
//        viewModel.getPurposeDataFromLocalDb()
//        binding.viewmodel = viewModel
//        viewModel.uiUpdatesPurposeItem.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
//            .collect { listItem ->
//                when (listItem) {
//                    is ResponseState.Error -> {
//                    }
//                    is ResponseState.Idle -> {
//                    }
//                    is ResponseState.Loading -> {
//                    }
//                    is ResponseState.Success -> {
//                        val mappedList = listItem.data!!.map {
//                            it!!
//                        }
//                        updateDataInExpandViewPurpose(mappedList)
//                    }
//                }
//            }
//    }
//
//    private fun updateDataInExpandView(data: List<AccountsListItem>) {
//        binding.expandAccView.rvItemslist.adapter = AccountItemsAdapter { item, _ ->
//            showLog(item.toString())
//            viewModel.currentSelectAccount.value = item.toAccountItem()
//            viewModel.isAccountSelected = true
//        }.apply {
//            submitList(data)
//        }
//    }
//
//    private fun updateDataInExpandViewPurpose(data: List<PurposeListItem>?) {
//        binding.expandPurposeView.rvItemslist.adapter = PurposeItemsAdapter { item, _ ->
//            showLog(item.toString())
//            viewModel.isPurposeSelected = true
//            viewModel.currentTransPurpose.value = item
//        }.apply {
//            submitList(data)
//        }
//    }

    override fun getToolbar() = ToolBarModel("Pay", searchClick = {
        showLog("1 Search Click")
    }, userImgClick = {
        showLog("1 Img CLick")
    })


    @Preview
    @Composable
    fun ShowMainUiPreview() {
        ShowMainUi()
    }

    @Composable
    fun ShowMainUi() {

        var isCollapsed by remember {
            mutableStateOf(true)
        }
        val standPaddingBottom = 8.dp
        val expandableView = ExpandablePurposeAccountView().apply {
            _viewModel = this@PayNowFragment.viewModel
        }
        Column {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(
                        rememberScrollState()
                    ), verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = getString(R.string.pay_from),
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.santas_grey)
                )
                //  Spacer(modifier = Modifier.height(standPaddingBottom))
                expandableView.ComponentAccountListItem()
                //  Spacer(modifier = Modifier.height(standPaddingBottom))
                Text(
                    text = getString(R.string.pat_to),
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.santas_grey)
                )
                //// Spacer(modifier = Modifier.size(6.dp))
                CraditCardView().apply {
                    val (icon, nam, accNo, bnknam) = payeesDetails.payesDetails!!
                    iconID.value=icon.toInt()
                    allowToShowSideButton.value=false
                    topTitle.value = nam
                    acNo.value = accNo
                    bankName.value = bnknam
                }.CraditCardViewUi(false)
                  Spacer(modifier = Modifier.size(6.dp))
                expandableView.ComponentPurposeListItem()

            }
            BottomButton({
//                val dir =
//                    PayeesDetailsFragmentDirections.actionPayeesDetailsFragmentToPayNowFragment()
//                dir.payesDetails = payessDetails.accountDetails
//                findNavController().safeNavigate(dir)
            }, Modifier.padding(horizontal = 16.dp))
        }

    }

    @Composable
    fun BottomButton(callOnClick: () -> Unit, modifier: Modifier = Modifier) {
        Button(
            onClick = { callOnClick() },
            modifier = modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth()
                .height(36.dp)
                .clip(RoundedCornerShape(16.dp)),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(
                    id = R.color.lochmara
                )
            )
        ) {
            Text(text = "Continue", color = Color.White)
        }
    }

}